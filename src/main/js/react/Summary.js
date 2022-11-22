import React from "react";
import axios from "axios";
import { useParams, Link } from 'react-router-dom';
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function Summary() {

    const { cartId } = useParams()

    const [order, setOrder] = React.useState({})
    const [item, setItem] = React.useState([{}])
    const [shipment, setShipment] = React.useState([])
    const [payment, setPayment] = React.useState([])

    React.useEffect(async () => {
        await axios
            .get("http://localhost:8080/api/order/" + cartId)
            .then(res => {
                setOrder(res.data)
                setShipment(res.data.shipmentsId)
                setPayment(res.data.paymentId)
            })
            .catch(err => {
                console.log(err.response)
            })
    }, [])

    React.useEffect(async () => {
         await axios
            .get("http://localhost:8080/api/cart/getByCartId/" + cartId)
            .then(res => {
                setItem(res.data)
            })
            .catch(err => {
                console.log(err.response)
            })
    }, [])

    const itemDetails = item.map(item => {
        return <div key={item.id} className="summary-order_card">
                 <img src= {discount_picture_2} />
                 <div className="summary-order_card-details">
                    <ul>
                        <li>{item.itemName}</li>
                        <li>{item.price} zł</li>
                    </ul>
                </div>
            </div>
    })

    return (
        <div className="summary">
            <div className="summary-user_details">
                <ul>
                    <li>{order.username}</li>
                    <li>{order.surname}</li>
                    <li>{order.userEmail}</li>
                    <li>{order.street}</li>
                    <li>{order.zipCode}</li>
                    <li>{order.city}</li>
                    <li>{order.voivodeship}</li>
                </ul>
                <ul>
                    <li>Numer zamówienia: {order.id}</li>
                    <li>Data: {order.orderDate}</li>
                    <li>Rodzaj wysyłki: {shipment.name}</li>
                    <li>Metoda płatności: {payment.name}</li>
                    <li>Status: {order.status}</li>
                </ul>
            </div>
            <div className="summary-order_card-section">
                {itemDetails}
           </div>
           <div className="summary-submit">
            <Link to="/">Anuluj zamówienie</Link>
            <Link to="/">Opłać zamówienie</Link>
           </div>
        </div>
    )
}

export default Summary
