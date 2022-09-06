import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";

function ForgotPassword() {

    const navigate = useNavigate()

    const [emailDetails, setEmailDetails] = React.useState({
        recipient: "",
        msgBody: "Witaj! \n\nAby zmienić hasło kliknij w link poniżej: link \n\n Życzymy udanych zakupów, \nZespół Yggdrasil",
        subject: "Prośba o zmianę hasła"
    })

    function handleChange(event) {
        setEmailDetails(prevEmailDetails => {
            return {
                ...prevEmailDetails,
                recipient: event.target.value
            }
        })
    }

    const handleSubmit = event => {
        event.preventDefault()

        axios
            .post("http://localhost:8080/api/mail/sendMail", emailDetails)
            .then(res => {
                console.log(res.data)
                navigate("/resetPasswordEmailSent")
            })
            .catch(err => {
                console.log(err.response)
            })
    }

    return (
    <div>
       <Header />
       <section className="forgotPassword-section">
        <form onSubmit={handleSubmit}>
            <input type="email" placeholder="Email" name="email" onChange={handleChange} />
            <button>Zmień hasło</button>
        </form>
       </section>
       <Footer />
     </div>
    )
}

export default ForgotPassword
