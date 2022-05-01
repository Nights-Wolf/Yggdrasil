import React from "react"

function Header() {
    const [mobileNavVisibility, setVisibility] = React.useState(false)

        function showNavbar() {
            setVisibility(prevShow => !prevShow)
        }

        const navVisibility = {
            transform: mobileNavVisibility ? 'translate(0, 0)' : 'translate(-999px, 0)',
            zIndex: mobileNavVisibility ? '2' : '0'
        }

        return(
            <header>
                <div className="menu-btn">
                <span className="menu-btn__hamburger" onClick={showNavbar}></span>
                </div>
                <nav style={navVisibility}>
                   <a href="/">Bursztyn</a>
                    <a href="/">Topaz</a>
                    <a href="/">Ametyst</a>
                    <a href="/">Lapiz Lazuli</a>
                    <a href="/">Kwarc</a>
                    <a href="/">Kryształ Górski</a>
                    <div className="nav-btn__login"><a href="/">Zaloguj się</a></div>
                    <div className="nav-btn__register"><a href="/">Zarejestruj się</a></div>
                </nav>
                <form className="search-engine">
                    <button><i class="fa fa-eye"></i></button>
                    <input type="text"/>
                </form>
            </header>
)}



export default Header
