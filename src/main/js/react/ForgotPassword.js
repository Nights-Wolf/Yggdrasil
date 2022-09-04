import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";

function ForgotPassword() {

    return (
    <div>
       <Header />
       <section className="forgotPassword-section">
        <form>
            <input type="email" placeholder="Email" name="email" />
            <button><Link to="/resetPasswordEmailSent">Zmień hasło </Link></button>
        </form>
       </section>
       <Footer />
     </div>
    )
}

export default ForgotPassword
