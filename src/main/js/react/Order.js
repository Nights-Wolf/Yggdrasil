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
        userEmail: "",
        orderDate: "",
        street: "",
        zipCode: "",
        city: "",
        voivodeship: "",
        status: "",
        shipment: ""
    })

    const [chosenShipment, setChosenShipment] = React.useState({
        shipment: ""
    })
    const [shipment, setShipment] = React.useState([{}])

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

        setChosenShipment(prevChosenShipment => {
            return {
                ...prevChosenShipment,
                shipment: [value]
            }
        })
    }

    const handleSubmit = (event) => {
        event.preventDefault()
    }

    const shipmentOptions = shipment.map(shipment => {
        return  <div className="shipment-option"> <label htmlFor={shipment.id}>{shipment.name}<span>{shipment.price} zł</span></label>
        <input key={shipment.id} id={shipment.id} type="radio" value={shipment.id} name="shipment" onChange={handleChange} /></div>
    })

console.log(chosenShipment)
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
