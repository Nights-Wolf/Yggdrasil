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

    function handleChange(event) {
        const {name, value} = event.target
        setLogin(prevLogin => {
            return {
                ...prevLogin,
                [name]: value
            }
        })
    }

    const handleSubmit = event => {
    event.preventDefault()

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

    return (
    <div>
       <Header />
       <section className="login-section">
        <form onSubmit={handleSubmit}>
            <input type="email" placeholder="Email" name="email" onChange={handleChange} />
            <input type="password" placeholder="Hasło" name="password" onChange={handleChange} />
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
