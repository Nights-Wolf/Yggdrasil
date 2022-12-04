import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function ForgotPassword() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

    const navigate = useNavigate()

    const [emailDetails, setEmailDetails] = React.useState({
        recipient: "",
        msgBody: "",
        subject: "Prośba o zmianę hasła"
    })

    const [error, setError] = React.useState({
            recipient: "",
        })

        const errorVisible = {
            color: "red"
        }

        const errorInvisible = {
            color: "default"
        }

    function handleChange(event) {
        setEmailDetails(prevEmailDetails => {
            return {
                ...prevEmailDetails,
                recipient: event.target.value
            }
        })
    }

    function checkEmail(email) {
            if (email == "") {
                setError(prevError => {
                    return {
                        ...prevError,
                        recipient: "Podaj email!"
                    }
                })
                return false
            }
            setError(prevError => {
                return {
                    ...prevError,
                    recipient: ""
                    }
            })
            return true
    }

    const handleSubmit = event => {
        event.preventDefault()

        const uuid = require('uuid')
        const token = uuid.v1()
        const today = new Date(Date.now())
        const expirationTime = today.getTime() + 1000000
        const isEmailCorrect = checkEmail(emailDetails.recipient)

        if(isEmailCorrect) {
            axios
                .post("http://localhost:8080/api/mail/sendMail/" + token, emailDetails)
                .catch(err => {
                    console.log(err.response)
                })

            axios
                .post("http://localhost:8080/api/resetPasswordToken/add", {
                email: emailDetails.recipient,
                token: token,
                expirationDate: expirationTime
                })
                .then(res => {
                    navigate("/resetPasswordEmailSent")
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
       <section className="forgotPassword-section">
        <form onSubmit={handleSubmit}>
            <input type="email" style={error.recipient === "" ? errorInvisible : errorVisible} placeholder={error.recipient === "" ? "Email*" : error.recipient} name="email" onChange={handleChange} aria-label="Podaj adres email aby zmienić hasło" />
            <button aria-label="Potwierdź zmianę hasła (link wysłany na email)" aria-required="true" >Wyślij link do zmiany hasła</button>
        </form>
       </section>
       <Footer />
     </div>
    )
}

export default ForgotPassword
