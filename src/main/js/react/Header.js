import React from "react";
import { Link, NavLink } from "react-router-dom";
import logo from "./assets/images/logo.png";

function Header(props) {

    const [mobileNavVisibility, setVisibility] = React.useState(false)

    const logOut = () => {
        const accessToken = localStorage.getItem("access_token")
        const refreshToken = localStorage.getItem("refresh_token")

        if (accessToken && refreshToken) {
            localStorage.removeItem("access_token")
            localStorage.removeItem("refresh_token")

            window.location.reload(false)
        }
    }

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
                <div>
                    <Link to="/"><img src={logo}></img></Link>
                </div>
                <nav style={navVisibility}>
                    <NavLink to="/products" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Produkty</NavLink>
                    <p>Kategorie</p>
                    <NavLink to="/products/amber" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Bursztyn</NavLink>
                    <NavLink to="/products/topaz" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Topaz</NavLink>
                    <NavLink to="/products/amethyst" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Ametyst</NavLink>
                    <NavLink to="/products/lapiz_lazuli" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Lapiz Lazuli</NavLink>
                    <NavLink to="/products/quartz" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Kwarc</NavLink>
                    <NavLink to="/products/mountain_gem" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Kryształ Górski</NavLink>
                    <div className="nav-btn__login">{props.isLogged === false ? <NavLink to="/login" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Zaloguj się</NavLink> : <NavLink to="/" style={({ isActive })=>isActive ? {background: '#0F9F49'} : null}>Moje konto</NavLink>}</div>
                    <div className="nav-btn__register">{props.isLogged === false ? <NavLink to="/register" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Zarejestruj się</NavLink> : <button onClick={logOut}>Wyloguj się</button>}</div>
                </nav>
                <form className="search-engine">
                    <button><i className="fa fa-eye"></i></button>
                    <input type="text"/>
                </form>
            </header>
)}

export default Header
