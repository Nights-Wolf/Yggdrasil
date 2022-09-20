import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { useParams } from "react-router-dom";

function RemindPassword() {

const { token } = useParams()

 React.useEffect(async () => {
           await axios
            .get("http://localhost:8080/api/resetPasswordToken/check/" + token)
            .then(res => {
                const user = res.headers.user

                localStorage.setItem('user', user)
            })
            .catch(err => {
                console.log(err)
            })
        }, [])

 const [remindPassword, setRemindPassword] = React.useState({
        newPassword: ""
    })

    const [passwordChecker, setPasswordChecker] = React.useState({
        repeatNewPassword: ""
    })

    const [error, setError] = React.useState({
        newPassword: "",
        repeatNewPassword: ""
    })

    const errorVisible = {
        color: "red"
    }

    const errorInvisible = {
        color: "default"
    }

    function handleChange(event) {
        const {name, value, type, checked} = event.target
        setRemindPassword(prevRemindPassword => {
            return {
                ...prevRemindPassword,
                [name]: type === "checkbox" ? checked : value
            }
        })
    }

        function checkNewPassword(passwordChecker) {
             if (passwordChecker != remindPassword.newPassword) {
                setError(prevError => {
                    return {
                        ...prevError,
                        repeatPassword: "Wpisz poprawne hasło!"
                    }
                })
                return false
             }
             setError(prevError => {
                return {
                    ...prevError,
                    repeatPassword: ""
                }
             })
             return true
            }

    function checkPasswordLength(password) {
        if (password.length <= 5 || password == "") {
            setError(prevError => {
                return {
                    ...prevError,
                    password: "Hasło musi zawierać co najmnniej 6 znaków!"
                }
            })
            return false
        }
        setError(prevError => {
            return {
                ...prevError,
                password: ""
            }
        })
        return true
    }

    const handleSubmit = event => {
            event.preventDefault()

            const isNewPasswordCorrect = checkNewPassword(remindPassword.newPassword)
            const isPasswordLengthCorrect = checkPasswordLength(remindPassword.newPassword)
            const user = localStorage.getItem("user")

            if (isPasswordLengthCorrect && isNewPasswordCorrect) {
                axios
                    .put("http://localhost:8080/api/user/changePassword/" + user, {
                    password: remindPassword.newPassword})
                    .then(res => {
                        navigate('/login')
                    })
                    .catch(err => {
                        console.log(err.response)
                })
            }
          }

    return (
    <div>
       <Header />
       <section className="remindPassword-section">
        <form onSubmit={handleSubmit}>
            <input type="password" style={error.newPassword === "" ? errorInvisible : errorVisible} placeholder={error.newPassword === "" ? "Nowe hasło" : error.newPassword} id="newPassword"  name="newPassword"  onChange={handleChange}/>
            <input type="password" style={error.repeatNewPassword === "" ? errorInvisible : errorVisible} placeholder={error.repeatNewPassword === "" ? "Powtórz hasło" : error.repeatNewPassword} id="repeatNewPassword"  name="repeatNewPassword" onChange={handleChange}/>
            <button>Zmień hasło</button>
        </form>
       </section>
       <Footer />
     </div>
    )
}

export default RemindPassword
