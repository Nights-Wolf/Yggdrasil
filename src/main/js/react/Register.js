import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link } from "react-router-dom";

function Register() {

    const [user, setUser] = React.useState({
        grantedAuthorities: "USER",
        username: "",
        surname: "",
        password: "",
        email: "",
        street: "",
        zipCode: "",
        voivodeship: "",
    })

    const [passwordChecker, setPasswordChecker] = React.useState({
        repeatPassword: ""
    })

    function validatePassword(event) {
        setPasswordChecker(prevPasswordChecker => {
            return {
                ...prevPasswordChecker,
                [event.target.name]: event.target.value
            }
        })
        console.log(passwordChecker.repeatPassword)
        checkPassword
    }

    const checkPassword = () => {
     if (passwordChecker.repeatPassword != user.password) {
                alert("Password doesn't match!")
            }
    }

    function handleChange(event) {
        setUser(prevUser => {
            return {
                ...prevUser,
                [event.target.name]: event.target.value
            }
        })
    }
console.log(user)
     const handleSubmit = event => {
     event.preventDefault()

           axios
            .post("http://localhost:8080/api/user/create", user)
            .then(res => {
                setUser(res.data)
            })
            .catch(err => {
                console.log(err)
            })
        }

    return (
    <div>
       <Header />
       <section className="register-section">
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Imię*" name="username" onChange={handleChange} />
            <input type="text" placeholder="Nazwisko*" name="surname" onChange={handleChange} />
            <input type="email" placeholder="Email*" name="email" onChange={handleChange} />
            <input type="password" placeholder="Hasło*" name="password" onChange={handleChange} />
            <input type="password" placeholder="Powtórz hasło*" name="repeatPassword" onChange={validatePassword} />
            <input type="text" placeholder="Adres" name="address" onChange={handleChange} />
            <input type="number" placeholder="Kod pocztowy" name="zipCode" onChange={handleChange} />
            <input type="text" placeholder="Miasto" name="city" onChange={handleChange} />
            <input type="text" placeholder="Województwo" name="voivodeship" onChange={handleChange} />
            <div className="checkbox">
            <input type="checkbox" id="terms"  name="terms" />
            <label htmlFor="terms"><span className="required">*</span> Oświadczam, że znam i akceptuję postanowienia <Link to="/terms">Regulaminu</Link>.</label>
            </div>
            <button>Zarejestruj się</button>
        </form>
        <Link to="/login" className="alreadyHaveAccount">Masz już konto? Zaloguj się!</Link>
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Register
