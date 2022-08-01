import Header from "./Header";
import Footer from "./Footer";
import { Link } from "react-router-dom";

function Register() {
    return (
    <div>
       <Header />
       <section className="register-section">
        <form>
            <input type="email" placeholder="Email" name="email" />
            <input type="password" placeholder="Hasło" name="password" />
            <input type="password" placeholder="Powtórz hasło" name="repeatPassword" />
            <input type="text" placeholder="Adres" name="address" />
            <input type="number" placeholder="Kod pocztowy" name="zipCode" />
            <input type="text" placeholder="Miasto" name="city" />
            <input type="text" placeholder="Województwo" name="voivodeship" />
            <button>Zarejestruj się</button>
        </form>
        <Link to="/login">Masz już konto? Zaloguj się!</Link>
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Register
