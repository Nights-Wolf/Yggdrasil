import React from "react";
import { Link, NavLink } from "react-router-dom";
import logo from "./assets/images/logo.png";
import useWindowDimensions from "./useWindowDimensions";

function Header(props) {

    const { height, width } = useWindowDimensions();

    const [mobileNavVisibility, setVisibility] = React.useState(false)
    const [profileNavVisibility, setProfileVisibility] = React.useState(false)

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

        const navVisibilityBiggerScreen = {
            transform: 'translate(0, 0)'
        }

        function showProfileNav() {
            setProfileVisibility(prevVisibility => !prevVisibility)
            }
        const navProfileVisibility = {
            display: profileNavVisibility ? 'block' : 'none'
        }

        const cartItemsCountVisibility = {
            display: props.cartItems >= 1 ? 'block' : 'none'
        }

        return(
            <header>
                <div className="menu-btn">
                <span className="menu-btn__hamburger" onClick={showNavbar}></span>
                </div>
                <div className="logo">
                    <Link to="/"><img src={logo}></img></Link>
                    <h1>Yggdrasil</h1>
                </div>
                <nav style={width >= 768 ? navVisibilityBiggerScreen : navVisibility}>
                    <NavLink to="/products" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Produkty</NavLink>
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
                    <div className="nav-profile" style={navProfileVisibility}>
                        <ul>
                            <li><NavLink to="/editProfile">Edytuj profil</NavLink></li>
                            <li><NavLink to="/myOrders">Moje zamówienia</NavLink></li>
                            <li onClick={logOut}>Wyloguj się</li>
                        </ul>
                    </div>
                    <div className={props.isLogged === false ? "nav-btn__login" : "nav-btn__my-account"}>{props.isLogged === false ? <NavLink to="/login" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Zaloguj się</NavLink> : <button onClick={showProfileNav}>Moje konto</button>}</div>
                    <div className={props.isLogged === false ? "nav-btn__register" : "nav-btn__register-hidden"}>{props.isLogged === false ? <NavLink to="/register" style={({ isActive })=>
                    isActive ? {background: '#0F9F49'} : null}>Zarejestruj się</NavLink> : null}</div>
                </nav>
                <div className="cart">
                    <Link to="/cart"><i class="fas fa-shopping-cart fa-2x"></i></Link>
                    <span className="cart-count" style={cartItemsCountVisibility}>{props.cartItems}</span>
                </div>
            </header>
)}

export default Header
