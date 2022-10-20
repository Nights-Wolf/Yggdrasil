import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";

function Order() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    const [user, setUser] = React.useState({})
    const [order, setOrder] = React.useState({
        orderValue: "",
        itemId: "",
        itemName: "",
        userId: "",
        userEmail: "",
        orderDate: "",
        street: "",
        zipCode: "",
        city: "",
        voivodeship: "",
        status: "",
        shipment: ""
    })
    const [shipment, setShipment] = React.useState({})

    function handleChange(event) {
        const {name, value, type, checked} = event.target
        setOrder(prevOrder => {
            return {
                ...prevOrder,
                [name]: value
            }
        })

        setShipment(prevShipment => {
            return {
                ...prevShipment,
                [name]: type === "checkbox" ? checked : value
            }
        })
    }

    const handleSubmit = (event) => {
        event.preventDefault()
    }

    return(
        <div>
        <Header
            isLogged={data}
            cartItems={cartItemsData}
        />
        <section className="order-section">
                <form onSubmit={handleSubmit}>
                    <input type="text" placeholder="Adres" name="street" onChange={handleChange} />
                    <input type="text" placeholder="Kod pocztowy" name="zipCode" onChange={handleChange} />
                    <input type="text" placeholder="Miasto" name="city" onChange={handleChange} />
                    <input type="text" placeholder="Województwo" name="voivodeship" onChange={handleChange} />
                </form>
        </section>
        <Footer />
        </div>
    )
}

export default Order
