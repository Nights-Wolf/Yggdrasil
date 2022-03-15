import React from "react"

function Header() {
    return(
        <header>
            <h1>LOGO JAK BĘDZIE</h1>
            <form className="search-engine">
                <input type="text"/>
            </form>
            <div className="login-panel">
                <a href="/">Zaloguj się!</a>
                <a href="/">Zarejestruj się!</a>
            </div>
            <div className="hamburger--container">
                <span className="hamburger"></span>
                <span className="hamburger"></span>
                <span className="hamburger"></span>
            </div>
        </header>
)}


export default Header