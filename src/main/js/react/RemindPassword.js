import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";

function RemindPassword() {

 const [remindPassword, setRemindPassword] = React.useState({
        email: "",
        password: "",
        newPassword: ""
    })

    const [passwordChecker, setPasswordChecker] = React.useState({
        repeatNewPassword: ""
    })

    const [error, setError] = React.useState({
        email: "",
        password: "",
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

    function checkPassword(email, passwordChecker) {
            const user = ""
            axios
                .get("api/user/checkPassword/" + email +"/" + passwordChecker)
                .then(res => {
                user = res.data
                })
            .catch(err => {
                console.log(err.response)
                })

         if (passwordChecker != user.password) {
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

        function checkNewPassword(passwordChecker) {
             if (passwordChecker != remindPassword.password) {
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

    function checkEmail(email) {
        const user = ""
        axios
            .get("api/user/getByEmail/" + email)
            .then(res => {
                user = res.data
                console.log(res.data)
                console.log(user)
            })
            .catch(err => {
                console.log(err.response)
            })

        if (user == "" || user == null) {
            setError(prevError => {
                return {
                    ...prevError,
                    email: "Podaj prawidłowy email!"
                }
            })
            console.log("ZŁY EMAIL")
            return false
        }
        setError(prevError => {
            return {
                ...prevError,
                email: ""
                }
        })
        return true
    }

    const handleSubmit = event => {
            event.preventDefault()

            const isEmailCorrect = checkEmail(remindPassword.email)
            const isPasswordCorrect = checkPassword(remindPassword.email, remindPassword.password)
            const isNewPasswordCorrect = checkNewPassword(remindPassword.newPassword)
            const isPasswordLengthCorrect = checkPasswordLength(remindPassword.newPassword)

            if (isEmailCorrect && isPasswordCorrect && isNewPasswordCorrect) {
                axios
                    .post("http://localhost:8080/api/user/changePassword/" + login.email, {email: login.email,
                    password: login.password})
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
            <input type="email" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.email === "" ? "Email" : error.email} name="email" onChange={handleChange} />
            <input type="password" style={error.password === "" ? errorInvisible : errorVisible} placeholder={error.password === "" ? "Hasło" : error.password} name="password" onChange={handleChange} />
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
