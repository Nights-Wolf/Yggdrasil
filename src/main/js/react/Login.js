import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";

function Login() {

    const navigate = useNavigate();

    const [login, setLogin] = React.useState({
        email: "",
        password: ""
    })

    const [error, setError] = React.useState({
        email: "",
        password: ""
    })

    const errorVisible = {
        color: "red"
    }

    const errorInvisible = {
        color: "default"
    }

    function handleChange(event) {
        const {name, value} = event.target
        setLogin(prevLogin => {
            return {
                ...prevLogin,
                [name]: value
            }
        })
    }

    function checkEmail(email) {
        if (email == "") {
            setError(prevError => {
                return {
                    ...prevError,
                    email: "Podaj prawidłowy email!"
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

        const isEmailCorrect = checkEmail(login.email)
        const isPasswordCorrect = checkPassword(login.password)

        if(isEmailCorrect && isPasswordCorrect) {
            axios
                .post("http://localhost:8080/api/login", login)
                .then(res => {
                    const token = res.data
                    localStorage.setItem('Token', token)

                    navigate('/')
                })
                .catch(err => {
                    console.log(err.response)
            })
        }
    }

    return (
    <div>
       <Header />
       <section className="login-section">
        <form onSubmit={handleSubmit}>
            <input type="email" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.email === "" ? "Email" : error.email} name="email" onChange={handleChange} />
            <input type="password" style={error.password === "" ? errorInvisible : errorVisible} placeholder={error.password === "" ? "Hasło" : error.password} name="password" onChange={handleChange} />
            <div className="checkbox--password">
            <input type="checkbox" id="rememberPassword"  name="rememberPassword"/>
            <label htmlFor="rememberPassword">Zapamiętaj mnie.</label>
            </div>
            <button>Zaloguj się</button>
        </form>
        <Link to="/login" className="resetPassword">Nie pamiętam hasła</Link>
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Login
