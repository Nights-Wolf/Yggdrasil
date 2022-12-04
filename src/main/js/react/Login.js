import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";
import Alert from "./Alert";

function Login() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

    const navigate = useNavigate();

    const [alert, setAlert] = React.useState(false)
    const [login, setLogin] = React.useState({
        email: "",
        password: "",
        rememberAuth: false
    })

    const [error, setError] = React.useState({
        email: "",
        password: "",
    })

    const errorVisible = {
        color: "red"
    }

    const errorInvisible = {
        color: "default"
    }

    function handleChange(event) {
        const {name, value, type, checked} = event.target
        setLogin(prevLogin => {
            return {
                ...prevLogin,
                [name]: type === "checkbox" ? checked : value
            }
        })
    }

    function checkEmail(email) {
        if (email == "") {
            setError(prevError => {
                return {
                    ...prevError,
                    email: "Niepoprawny email!"
                }
            })
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

    function checkPassword(password) {
        if (password.length <= 5 || password == "") {
            setError(prevError => {
                return {
                    ...prevError,
                    password: "Hasło jest za krótkie!"
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

        const isEmailCorrect = checkEmail(login.email)
        const isPasswordCorrect = checkPassword(login.password)
        const rememberAuthChecked = login.rememberAuth

        if(isEmailCorrect && isPasswordCorrect && !rememberAuthChecked) {
            axios
                .post("http://localhost:8080/api/login", {email: login.email,
                password: login.password})
                .then(res => {
                   const accessToken = res.headers.access_token
                   const refreshToken = res.headers.refresh_token

                    localStorage.setItem('access_token', accessToken)
                    localStorage.setItem('refresh_token', refreshToken)

                    navigate('/')
                })
                .catch(err => {
                    setAlert(prevAlert => true)
            })
        } else if (isEmailCorrect && isPasswordCorrect && rememberAuthChecked) {
                    axios
                        .post("http://localhost:8080/api/authentication/remember/" + login.email)
                        .catch(err => {
                            setAlert(prevAlert => true)
                    })

            axios
                .post("http://localhost:8080/api/login", {email: login.email,
                password: login.password})
                .then(res => {
                   const accessToken = res.headers.access_token
                   const refreshToken = res.headers.refresh_token

                    localStorage.setItem('access_token', accessToken)
                    localStorage.setItem('refresh_token', refreshToken)

                    navigate('/')
                })
                .catch(err => {
                    setAlert(prevAlert => true)
            })
        }
    }

    return (
    <div>
       <Header
        isLogged={data}
        cartItems={cartItemsData}
         />
       <section className="login-section">
       <Alert
            isVisible={alert}
            alertText="Podany email lub hasło jest nieprawidłowe!" />
        <h1>Zaloguj się</h1>
        <form onSubmit={handleSubmit}>
            <input type="email" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.email === "" ? "Email" : error.email} name="email" onChange={handleChange} aria-label="Wpisz swój email podany przy rejestracji email" />
            <input type="password" style={error.password === "" ? errorInvisible : errorVisible} placeholder={error.password === "" ? "Hasło" : error.password} name="password" onChange={handleChange} aria-label="Wpisz swoje hasło podane przy rejestracji" />
            <div className="checkbox--password">
            <input type="checkbox" id="rememberAuth"  name="rememberAuth" checked={login.rememberAuth} onChange={handleChange} aria-label="Czy nie wylogowywać?" />
            <label htmlFor="rememberAuth">Zapamiętaj mnie.</label>
            </div>
            <button aria-label="Zaloguj się" aria-required="true" >Zaloguj się</button>
        </form>
        <Link to="/forgotPassword" className="remindPassword">Nie pamiętam hasła</Link>
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Login
