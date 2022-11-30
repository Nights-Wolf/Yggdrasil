import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function Login() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

    const navigate = useNavigate();

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
                    password: "Niepoprawne hasło!"
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
                    console.log(err.response)
            })
        } else if (isEmailCorrect && isPasswordCorrect && rememberAuthChecked) {
                    axios
                        .post("http://localhost:8080/api/authentication/remember/" + login.email)
                        .catch(err => {
                            console.log(err.response)
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
       <section className="login-section">
        <h1>Zaloguj się</h1>
        <form onSubmit={handleSubmit}>
            <input type="email" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.email === "" ? "Email" : error.email} name="email" onChange={handleChange} />
            <input type="password" style={error.password === "" ? errorInvisible : errorVisible} placeholder={error.password === "" ? "Hasło" : error.password} name="password" onChange={handleChange} />
            <div className="checkbox--password">
            <input type="checkbox" id="rememberAuth"  name="rememberAuth" checked={login.rememberAuth} onChange={handleChange}/>
            <label htmlFor="rememberAuth">Zapamiętaj mnie.</label>
            </div>
            <button>Zaloguj się</button>
        </form>
        <Link to="/forgotPassword" className="remindPassword">Nie pamiętam hasła</Link>
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Login
