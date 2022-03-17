import React from "react"

function Header() {
    return(
        <header>
             <div className="hamburger--container">
                <span className="hamburger"></span>
                <span className="hamburger"></span>
                <span className="hamburger"></span>
            </div>
            <h1>YGGDRASIL</h1>
            <form className="search-engine">
                <input type="text"/>
            </form>
            <div className="login-panel">
                <a href="/">Zaloguj się!</a>
                <a href="/">Zarejestruj się!</a>
                <a href="/">O nas</a>
                <a href="/">Kontakt</a>
            </div>
        </header>
)}


export default Header