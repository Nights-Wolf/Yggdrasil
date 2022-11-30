import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function EditProfile() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

    const navigate = useNavigate()

    const [user, setUser] = React.useState({
        email: "",
        username: "",
        surname: "",
        street: "",
        zipCode: "",
        city: "",
        voivodeship: "",
    })

    const [error, setError] = React.useState({
        username: "",
        surname: "",
    })

    const errorVisible = {
        color: "red"
    }

    const errorInvisible = {
        color: "default"
    }

    React.useEffect(() => {
        const accessToken = localStorage.getItem("access_token")

        axios
            .get("http://localhost:8080/api/user/getByToken", {headers: {
                Authorization: "Bearer " + accessToken}})
            .then(res => {
                 setUser(prevUser => {
                     return {
                        ...prevUser,
                        email: res.data.email,
                        username: res.data.username,
                        surname: res.data.surname,
                        street: res.data.street,
                        zipCode: res.data.zipCode,
                        city: res.data.city,
                        voivodeship: res.data.voivodeship
                 }})
            })
            .catch(err => {
                navigate("/")
    })
    }, [])

    function handleChange(event) {
        const {name, value, type, checked} = event.target
        setUser(prevUser => {
            return {
                ...prevUser,
                [name]: type === "checkbox" ? checked : value
            }
        })
    }

    function checkName(name) {
        if (name == "") {
            setError(prevError => {
            return {
                ...prevError,
                username: "Musisz podać imię!"
            }})
            return false
        }
        setError(prevError => {
            return {
                ...prevError,
                username: ""
        }})
        return true
    }

    function checkSurname(surname) {
        if (surname == "") {
            setError(prevError => {
                return {
                    ...prevError,
                    surname: "Musisz podać nazwisko!"
                }
            })
            return false
        }
        setError(prevError => {
            return {
                ...prevError,
                surname: ""
            }
        })
        return true
    }

     const handleSubmit = event => {

     event.preventDefault()

     const isNameCorrect = checkName(user.username)
     const isSurnameCorrect = checkSurname(user.surname)

     if (isNameCorrect && isSurnameCorrect) {
        axios
            .put("http://localhost:8080/api/user/edit", user)
            .then(window.location.reload(false))
            .catch(err => {
                navigate("/")
            })
        }
        }

    return (
    <div>
       <Header
        isLogged={data}
        cartItems={cartItemsData}
         />
       <section className="edit_profile-section">
        <form onSubmit={handleSubmit}>
            <input type="text" style={error.username === "" ? errorInvisible : errorVisible} placeholder={error.username === "" ? "Imię*" : error.username} value={user.username} name="username" onChange={handleChange} />
            <input type="text" style={error.surname === "" ? errorInvisible : errorVisible} placeholder={error.surname === "" ? "Nazwisko*" : error.surname} value={user.surname} name="surname" onChange={handleChange} />
            <input type="text" placeholder="Adres" value={user.street} name="street" onChange={handleChange} />
            <input type="text" placeholder="Kod pocztowy" value={user.zipCode} name="zipCode" onChange={handleChange} />
            <input type="text" placeholder="Miasto" value={user.city} name="city" onChange={handleChange} />
            <input type="text" placeholder="Województwo" value={user.voivodeship} name="voivodeship" onChange={handleChange} />
            <button>Zmień dane</button>
        </form>
        <Link to="/changePassword" className="change_login_data">Zmień hasło</Link>
        <Link to="/changeEmail" className="change_login_data">Edytuj Email</Link>
       </section>
       <Footer />
     </div>
    )
 }

 export default EditProfile
