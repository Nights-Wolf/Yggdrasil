import React from "react";
import { Link, NavLink } from "react-router-dom";
import logo from "../assets/images/logo.png";

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
                <div>
                    <Link to="/"><img src={logo}></img></Link>
                </div>
                <nav style={navVisibility}>
                    <NavLink to="/" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Bursztyn</NavLink>
                    <NavLink to="/" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Topaz</NavLink>
                    <NavLink to="/" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Ametyst</NavLink>
                    <NavLink to="/" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Lapiz Lazuli</NavLink>
                    <NavLink to="/" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Kwarc</NavLink>
                    <NavLink to="/" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Kryształ Górski</NavLink>
                    <div className="nav-btn__login"><NavLink to="/" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Zaloguj się</NavLink></div>
                    <div className="nav-btn__register"><NavLink to="/" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Zarejestruj się</NavLink></div>
                </nav>
                <form className="search-engine">
                    <button><i className="fa fa-eye"></i></button>
                    <input type="text"/>
                </form>
            </header>
)}



export default Header
