import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";

function Register() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    const navigate = useNavigate()

    const [emailDetails, setEmailDetails] = React.useState({
        recipient: "",
        msgBody: "",
        subject: ""
    })

    const [user, setUser] = React.useState({
        username: "",
        surname: "",
        password: "",
        email: "",
        street: "",
        city: "",
        zipCode: "",
        voivodeship: "",
        acceptedTerms: false,
        acceptedRodo: false
    })

    const [passwordChecker, setPasswordChecker] = React.useState({
        repeatPassword: ""
    })

    const [error, setError] = React.useState({
        username: "",
        surname: "",
        email: "",
        password: "",
        repeatPassword: ""
    })

    const errorVisible = {
        color: "red"
    }

    const errorInvisible = {
        color: "default"
    }

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

    function checkName(name) {
        if (name == "") {
            setError(prevError => {
            return {
                ...prevError,
                username: "Musisz podać imię!"
            }})
            return false
        }
        setError(prevError => {
            return {
                ...prevError,
                username: ""
        }})
        return true
    }

    function checkSurname(surname) {
        if (surname == "") {
            setError(prevError => {
                return {
                    ...prevError,
                    surname: "Musisz podać nazwisko!"
                }
            })
            return false
        }
        setError(prevError => {
            return {
                ...prevError,
                surname: ""
            }
        })
        return true
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
                    axios
                        .post("http://localhost:8080/api/mail/sendRegistrationMail", {
                                recipient: user.email,
                                msgBody: "Witaj " + user.username + "\n\n Pragniemy poinformować Cię, że Twoje konto zostało utworzone! \n\n Dziękujemy, że jesteś z nami! \n\n ~Zespół Yggdrasil",
                                subject: "Konto zostało utworzone"
                        })
                navigate('/login')
                }
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
       <section className="register-section">
       <h1>Zarejestruj się</h1>
        <form onSubmit={handleSubmit}>
            <input type="text" style={error.username === "" ? errorInvisible : errorVisible} placeholder={error.username === "" ? "Imię*" : error.username} name="username" onChange={handleChange} />
            <input type="text" style={error.surname === "" ? errorInvisible : errorVisible} placeholder={error.surname === "" ? "Nazwisko*" : error.surname} name="surname" onChange={handleChange} />
            <input type="email" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.email === "" ? "Email*" : error.email} name="email" onChange={handleChange} />
            <input type="password" style={error.password === "" ? errorInvisible : errorVisible} placeholder={error.password === "" ? "Hasło*" : error.password} name="password" onChange={handleChange} />
            <input type="password" style={error.repeatPassword === "" ? errorInvisible : errorVisible} placeholder={error.repeatPassword === "" ? "Powtórz hasło*" : error.repeatPassword} name="repeatPassword" onChange={validatePassword} />
            <input type="text" placeholder="Adres" name="street" onChange={handleChange} />
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
