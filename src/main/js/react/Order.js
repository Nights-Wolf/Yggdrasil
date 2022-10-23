import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";

function Order() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    const [user, setUser] = React.useState({
        email: ""
    })
    const [order, setOrder] = React.useState({
        orderValue: "",
        itemId: "",
        itemName: "",
        userId: "",
        username: "",
        surname: "",
        userEmail: "",
        orderDate: "",
        street: "",
        zipCode: "",
        city: "",
        voivodeship: "",
        status: "",
        shipment: ""
    })

    const [shipment, setShipment] = React.useState([{}])

    const [error, setError] = React.useState({
        username: "",
        surname: "",
        email: "",
        street: "",
        zipCode: "",
        city: "",
        voivodeship: ""
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
                        }})
                    setOrder(prevOrder => {
                        return {
                            ...prevOrder,
                            userId: res.data.id,
                            username: res.data.username,
                            surname: res.data.surname,
                            userEmail: res.data.email,
                            street: res.data.street,
                            zipCode: res.data.zipCode,
                            city: res.data.city,
                            voivodeship: res.data.voivodeship
                        }
                    })
                })
                .catch(err => {
                    console.log(err.response)
        })
    }, [])

    React.useEffect(async () => {
        await axios
            .get("http://localhost:8080/api/shipments")
            .then(res => {
                setShipment(res.data)
            })
            .catch(err => {
                console.log(err.response)
            })
    }, [])

    function handleChange(event) {
        const {name, value} = event.target
        setOrder(prevOrder => {
            return {
                ...prevOrder,
                [name]: value
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

        function checkEmail(email) {
            if (email == "") {
                setError(prevError => {
                    return {
                        ...prevError,
                        email: "Podaj prawidłowy email!"
                    }
                })
                return false
            }
            setError(prevError => {
                return {
                    ...prevError,
                    email: ""
                    }
            })
            return true
        }

        function checkStreet(street) {
            if (street == "") {
                setError(prevError => {
                    return {
                        ...prevError,
                        street: "Podaj adres!"
                    }
                })
                return false
            }
            setError(prevError => {
                return {
                    ...prevError,
                    street: ""
                    }
            })
            return true
        }

        function checkZipCode(zipCode) {
            if (zipCode == "") {
                setError(prevError => {
                    return {
                        ...prevError,
                        zipCode: "Podaj prawidłowy email!"
                    }
                })
                return false
            }
            setError(prevError => {
                return {
                    ...prevError,
                    zipCode: ""
                    }
            })
            return true
        }

        function checkCity(city) {
            if (city == "") {
                setError(prevError => {
                    return {
                        ...prevError,
                        city: "Podaj miasto!"
                    }
                })
                return false
            }
            setError(prevError => {
                return {
                    ...prevError,
                    city: ""
                }
            })
            return true
        }

        function checkVoivodeship(voivodeship) {
            if (voivodeship == "") {
                setError(prevError => {
                    return {
                        ...prevError,
                        voivodeship: "Podaj województwo!"
                    }
                })
                return false
            }
            setError(prevError => {
                return {
                    ...prevError,
                    voivodeship: ""
                }
            })
            return true
        }

    const handleSubmit = (event) => {
        event.preventDefault()

        const isNameCorrect = checkName(order.name)
        const isSurnameCorrect = checkSurname(order.surname)
        const isEmailCorrect = checkEmail(order.userEmail)
        const isStreetCorrect = checkStreet(order.street)
        const isZipCodeCorrect = checkZipCode(order.zipCode)
        const isCityCorrect = checkCity(order.city)
        const isVoivodeshipCorrect = checkVoivodeship(order.voivodeship)
    }

    const shipmentOptions = shipment.map(shipment => {
        return  <div className="shipment-option"> <label htmlFor={shipment.id}>{shipment.name}<span>({shipment.price} zł)</span></label>
        <input key={shipment.id} id={shipment.id} type="radio" value={shipment.id} name="shipment" onChange={handleChange} /></div>
    })

    return(
        <div>
        <Header
            isLogged={data}
            cartItems={cartItemsData}
        />
        <section className="order-section">
                <form onSubmit={handleSubmit}>
                    <input type="text" style={error.username === "" ? errorInvisible : errorVisible} placeholder={error.username === "" ? "Imię*" : error.username} value={order.username} name="username" onChange={handleChange} />
                    <input type="text" style={error.surname === "" ? errorInvisible : errorVisible} placeholder={error.surname === "" ? "Nazwisko*" : error.surname} value={order.surname} name="surname" onChange={handleChange} />
                    <input type="email" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.email === "" ? "Email*" : error.email} value={order.userEmail} name="email" onChange={handleChange} />
                    <input type="text" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.street === "" ? "Adres*" : error.street} value={order.street} name="street" onChange={handleChange} />
                    <input type="text" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.zipCode === "" ? "Kod pocztowy*" : error.zipCode} value={order.zipCode} name="zipCode" onChange={handleChange} />
                    <input type="text" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.city === "" ? "Miasto*" : error.city} value={order.city} onChange={handleChange} />
                    <input type="text" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.voivodeship === "" ? "Województwo*" : error.voivodeship} value={order.voivodeship} name="voivodeship" onChange={handleChange} />
                    <div className="shipment">
                        {shipmentOptions}
                    </div>
                </form>
        </section>
        <Footer />
        </div>
    )
}

export default Order
