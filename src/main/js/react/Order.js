import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";

function Order() {

    const navigate = useNavigate()
    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    const [user, setUser] = React.useState()
    const [cart, setCart] = React.useState()
    const [order, setOrder] = React.useState({
        orderValue: "",
        cartId: "",
        username: "",
        surname: "",
        userEmail: "",
        orderDate: "",
        street: "",
        zipCode: "",
        city: "",
        voivodeship: "",
        status: "Nieopłacone",
        shipmentsId: "",
        paymentId: ""
    })

    const [shipment, setShipment] = React.useState([{}])

    const [payments, setPayments] = React.useState([{}])

    const [error, setError] = React.useState({
        username: "",
        surname: "",
        email: "",
        street: "",
        zipCode: "",
        city: "",
        voivodeship: "",
        shipmentsId: "",
        paymentId: ""
    })

    const errorVisible = {
        color: "red"
    }

    const errorInvisible = {
        color: "black"
    }

    React.useEffect(() => {
        const accessToken = localStorage.getItem("access_token")

            axios
                .get("http://localhost:8080/api/user/getByToken", {headers: {
                    Authorization: "Bearer " + accessToken}})
                .then(res => {
                    setUser(res.data)
                    setOrder(prevOrder => {
                        return {
                            ...prevOrder,
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

    React.useEffect(async () => {
        await axios
            .get("http://localhost:8080/api/payment")
            .then(res => {
                setPayments(res.data)
            })
            .catch(err => {
                console.log(err.response)
            })
    }, [])

    React.useEffect(async () => {
        const cartToken = localStorage.getItem("cart")

        await axios
            .get("http://localhost:8080/api/cart/" + cartToken)
            .then(res => {
                setOrder(prevOrder => {
                    return {
                        ...prevOrder,
                        cartId: res.data
                    }
                })
                setCart(res.data)
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

        function handleShipment(event) {
            const {value} = event.target
            const chosenShipment = shipment[value - 1]
            setOrder(prevOrder => {
                return {
                    ...prevOrder,
                    shipmentsId: chosenShipment
                }
            })
        }

        function handlePayment(event) {
            const {value} = event.target
            const chosenPayment = payments[value - 1]
            setOrder(prevOrder => {
                return {
                    ...prevOrder,
                    paymentId: chosenPayment
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

        function checkShipment(shipment) {
                    if (shipment == "") {
                        setError(prevError => {
                        return {
                            ...prevError,
                            shipmentsId: "Musisz wybrać dostawcę!"
                        }})
                        return false
                    }
                    setError(prevError => {
                        return {
                            ...prevError,
                            shipmentsId: ""
                    }})
                    return true
                }

        function checkPayment(payment) {
                    if (payment == "") {
                        setError(prevError => {
                        return {
                            ...prevError,
                            paymentId: "Musisz wybrać sposób płatności!"
                        }})
                        return false
                    }
                    setError(prevError => {
                        return {
                            ...prevError,
                            paymentId: ""
                    }})
                    return true
                }

    const handleSubmit = (event) => {
        event.preventDefault()

        const date = new Date(Date.now())

        const isNameCorrect = checkName(order.name)
        const isSurnameCorrect = checkSurname(order.surname)
        const isEmailCorrect = checkEmail(order.userEmail)
        const isStreetCorrect = checkStreet(order.street)
        const isZipCodeCorrect = checkZipCode(order.zipCode)
        const isCityCorrect = checkCity(order.city)
        const isVoivodeshipCorrect = checkVoivodeship(order.voivodeship)
        const isShipmentCorrect = checkShipment(order.shipmentsId)
        const isPaymentCorrect = checkPayment(order.paymentId)

        if (isNameCorrect && isSurnameCorrect && isEmailCorrect && isStreetCorrect && isZipCodeCorrect && isCityCorrect
        && isVoivodeshipCorrect && isShipmentCorrect && isPaymentCorrect) {
            axios
                .post("http://localhost:8080/api/order", {
                    orderValue: order.orderValue,
                    cartId: order.cartId,
                    username: order.username,
                    surname: order.surname,
                    userEmail: order.userEmail,
                    orderDate: date,
                    street: order.street,
                    zipCode: order.zipCode,
                    city: order.city,
                    voivodeship: order.voivodeship,
                    status: order.status,
                    shipmentsId: order.shipmentsId,
                    paymentId: order.paymentId
                })
                .then(res => {
                    localStorage.removeItem("cart")
                    navigate("/summary/" + cart.id)
                })
                .catch(err => {
                    console.log(err.response)
                })
            }
    }

    const shipmentOptions = shipment.map(shipment => {
        return  <div className="shipment-option"  style={error.shipmentsId === "" ? errorInvisible : errorVisible}> <label htmlFor={shipment.id}>{shipment.name}<span>({shipment.price} zł)</span></label>
        <input key={shipment.id} id={shipment.id} type="radio" value={shipment.id} name="shipmentsId" onChange={handleShipment} /></div>
    })

    const paymentsOptions = payments.map(payment => {
        return  <div className="payment-option"  style={error.paymentId === "" ? errorInvisible : errorVisible}> <label htmlFor={payment.id}>{payment.name}</label>
        <input key={payment.id} id={payment.id} type="radio" value={payment.id} name="paymentId" onChange={handlePayment} /></div>
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
                    <input type="email" style={error.email === "" ? errorInvisible : errorVisible} placeholder={error.email === "" ? "Email*" : error.email} value={order.userEmail} name="userEmail" onChange={handleChange} />
                    <input type="text" style={error.street === "" ? errorInvisible : errorVisible} placeholder={error.street === "" ? "Adres*" : error.street} value={order.street} name="street" onChange={handleChange} />
                    <input type="text" style={error.zipCode === "" ? errorInvisible : errorVisible} placeholder={error.zipCode === "" ? "Kod pocztowy*" : error.zipCode} value={order.zipCode} name="zipCode" onChange={handleChange} />
                    <input type="text" style={error.city === "" ? errorInvisible : errorVisible} placeholder={error.city === "" ? "Miasto*" : error.city} value={order.city} name="city" onChange={handleChange} />
                    <input type="text" style={error.voivodeship === "" ? errorInvisible : errorVisible} placeholder={error.voivodeship === "" ? "Województwo*" : error.voivodeship} value={order.voivodeship} name="voivodeship" onChange={handleChange} />
                    <div className="shipment">
                        {shipmentOptions}
                    </div>
                    <div className="payment">
                        {paymentsOptions}
                    </div>
                    <div className="submit-order-buttons">
                        <Link to="/products">Kontynuuj zakupy</Link>
                        <button>Złóż zamówienie</button>
                    </div>
                </form>
        </section>
        <Footer />
        </div>
    )
}

export default Order
