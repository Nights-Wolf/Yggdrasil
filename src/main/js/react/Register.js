import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";

function Register() {

    const navigate = useNavigate();

    const [user, setUser] = React.useState({
        username: "",
        surname: "",
        password: "",
        email: "",
        street: "",
        zipCode: "",
        voivodeship: "",
        acceptedTerms: false,
        acceptedRodo: false
    })

    const [passwordChecker, setPasswordChecker] = React.useState({
        repeatPassword: ""
    })

    function handleChange(event) {
        const {name, value, type, checked} = event.target
        setUser(prevUser => {
            return {
                ...prevUser,
                [name]: type === "checkbox" ? checked : value
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

    function checkPassword(passwordChecker) {
     if (passwordChecker != user.password) {
        return false
     }
     return true
    }

    function checkPasswordLength(password) {
        if (password.length <= 5 || password == "") {
            return false
        }
        return true
    }

    function checkName(name) {
        if (name == "") {
            return false
        }
        return true
    }

    function checkSurname(surname) {
        if (surname == "") {
            return false
        }
        return true
    }

    function checkEmail(email) {
        if (email == "") {
            return false
        }
        return true
    }

     const handleSubmit = event => {

     event.preventDefault()
     const isPasswordLengthCorrect = checkPasswordLength(user.password)
     const isPasswordCorrect = checkPassword(passwordChecker.repeatPassword)
     const isNameCorrect = checkName(user.username)
     const isSurnameCorrect = checkSurname(user.surname)
     const isEmailCorrect = checkEmail(user.email)

     if (isPasswordCorrect && isPasswordLengthCorrect && isNameCorrect && isSurnameCorrect && isEmailCorrect) {
        axios
            .post("http://localhost:8080/api/user/create", user)
            .then(res => {
                if(res.status == 200) {
                navigate('/')
                }
            })
            .catch(err => {
                console.log(err.response)
            })
        }
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
            <input type="text" placeholder="Kod pocztowy" name="zipCode" onChange={handleChange} />
            <input type="text" placeholder="Miasto" name="city" onChange={handleChange} />
            <input type="text" placeholder="Województwo" name="voivodeship" onChange={handleChange} />
            <div className="checkbox">
            <input type="checkbox" id="acceptedTerms"  name="acceptedTerms" checked={user.acceptedTerms} onChange={handleChange} />
            <label htmlFor="acceptedTerms"><span className="required">*</span> Oświadczam, że znam i akceptuję postanowienia <Link to="/terms">Regulaminu</Link>.</label>
            <input type="checkbox" id="acceptedRodo"  name="acceptedRodo" checked={user.acceptedRodo} onChange={handleChange} />
            <label htmlFor="acceptedRodo"><span className="required">*</span> Oświadczam, że zapoznałem się z klauzulą <Link to="/rodo">RODO</Link>.</label>
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
