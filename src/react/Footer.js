import React from "react";
import { Link } from "react-router-dom";

function Footer() {
    return(
        <footer>
            <Link to="/terms">Regulamin</Link>
            <Link to="/rodo">RODO</Link>
            <Link to="/about">O nas</Link>
            <Link to="/contact">Kontakt</Link>
            <p>Copyright &copy;</p>
        </footer>
    )
}

export default Footer
