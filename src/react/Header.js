import React from "react"

function Header() {
    return(
        <header>
             <div className="hamburger--container">
                <span className="hamburger"></span>
                <span className="hamburger"></span>
                <span className="hamburger"></span>
            </div>
            <form className="search-engine">
                <input type="text"/>
                <button></button>
            </form>
        </header>
)}


export default Header