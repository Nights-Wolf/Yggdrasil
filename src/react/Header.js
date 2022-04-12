import React from "react"

function Header() {
    return(
        <header>
            <div className="menu-btn">
            <span className="menu-btn__hamburger"></span>
            </div>
            <form className="search-engine">
                <button><i class="fa fa-eye"></i></button>
                <input type="text"/>
            </form>
        </header>
)}


export default Header