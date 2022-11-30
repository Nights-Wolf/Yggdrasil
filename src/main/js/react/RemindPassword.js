import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { useParams, useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function RemindPassword() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

    const { token } = useParams()

    const navigate = useNavigate()

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
        const {name, value} = event.target
        setRemindPassword(prevRemindPassword => {
            return {
                ...prevRemindPassword,
                [name]: value
            }
        })
    }

    function validatePassword(event) {
        const {name, value} = event.target
        setPasswordChecker(prevPasswordChecker => {
            return {
                ...prevPasswordChecker,
                [name] : value
            }
        })
    }

        function checkNewPassword(passwordChecker) {
             if (passwordChecker != remindPassword.newPassword) {
                setError(prevError => {
                    return {
                        ...prevError,
                        repeatNewPassword: "Wpisz poprawne hasło!"
                    }
                })
                return false
             }
             setError(prevError => {
                return {
                    ...prevError,
                    repeatNewPassword: ""
                }
             })
             return true
            }

    function checkPasswordLength(password) {
        if (password.length <= 5 || password == "") {
            setError(prevError => {
                return {
                    ...prevError,
                    newPassword: "Hasło musi zawierać co najmnniej 6 znaków!"
                }
            })
            return false
        }
        setError(prevError => {
            return {
                ...prevError,
                newPassword: ""
            }
        })
        return true
    }

    const handleSubmit = event => {
            event.preventDefault()

            const isNewPasswordCorrect = checkNewPassword(passwordChecker.repeatNewPassword)
            const isPasswordLengthCorrect = checkPasswordLength(remindPassword.newPassword)
            const user = localStorage.getItem("user")

            if(isNewPasswordCorrect && isPasswordLengthCorrect) {
                axios
                    .put("http://localhost:8080/api/user/remindPassword/" + user, {
                    password: remindPassword.newPassword})
                    .then(res => {
                        localStorage.removeItem("user")
                        navigate('/login')
                    })
                    .catch(err => {
                        console.log(err.response)
                })
            }
          }

    return (
    <div>
       <Header
        isLogged={data}
        cartItems={cartItemsData}
        />
       <section className="remindPassword-section">
        <form onSubmit={handleSubmit}>
            <input type="password" style={error.newPassword === "" ? errorInvisible : errorVisible} placeholder={error.newPassword === "" ? "Nowe hasło" : error.newPassword} id="newPassword"  name="newPassword"  onChange={handleChange}/>
            <input type="password" style={error.repeatNewPassword === "" ? errorInvisible : errorVisible} placeholder={error.repeatNewPassword === "" ? "Powtórz hasło" : error.repeatNewPassword} id="repeatNewPassword"  name="repeatNewPassword" onChange={validatePassword}/>
            <button>Zmień hasło</button>
        </form>
       </section>
       <Footer />
     </div>
    )
}

export default RemindPassword
