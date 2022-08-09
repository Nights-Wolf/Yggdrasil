import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";

function Login() {
    return (
    <div>
       <Header />
       <section className="login-section">
        <form >
            <input type="email" placeholder="Email" name="email"  />
            <input type="password" placeholder="Hasło" name="password"  />
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