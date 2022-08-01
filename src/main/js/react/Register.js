import Header from "./Header";
import Footer from "./Footer";
import { Link } from "react-router-dom";

function Register() {
    return (
    <div>
       <Header />
       <section className="register-section">
        <form>
            <input type="text" placeholder="Imię*" name="username" />
            <input type="text" placeholder="Nazwisko*" name="surname" />
            <input type="email" placeholder="Email*" name="email" />
            <input type="password" placeholder="Hasło*" name="password" />
            <input type="password" placeholder="Powtórz hasło*" name="repeatPassword" />
            <input type="text" placeholder="Adres" name="address" />
            <input type="number" placeholder="Kod pocztowy" name="zipCode" />
            <input type="text" placeholder="Miasto" name="city" />
            <input type="text" placeholder="Województwo" name="voivodeship" />
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
