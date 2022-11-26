import React from "react";
import axios from "axios";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function MyOrders(props) {
    const [item, setItem] = React.useState([{}])
    React.useEffect(async () => {
        await
         axios
            .get("http://localhost:8080/api/cart/getByCartId/" + props.orderNum)
            .then(res => {
                setItem(res.data)
                })
            .catch(err => {
                console.log(err.response)
            })
    }, [])

    return(
            <div className="my-Order_card">
                <div className="order-details">
                    <ul>
                        <li>Numer zamówienia: {props.orderNum}</li>
                        <li>Przedmiot: {props.item}</li>
                        <li>Cena: {props.price}</li>
                        <li>Data: {props.date}</li>
                        <li>Status: {props.status}</li>
                        <li>Rodzaj dostawy: {props.shipment}</li>
                        <li>Płatność: {props.payment}</li>
                    </ul>
                    <ul>
                        <li>{props.username}</li>
                        <li>{props.surname}</li>
                        <li>Ulica: {props.street}</li>
                        <li>Kod pocztowy: {props.zipCode}</li>
                        <li>Miasto: {props.city}</li>
                        <li>Województwo: {props.voivodeship}</li>
                    </ul>
                </div>
                <div className="item-section">
                            {item.map(item => {
                                return (
                                    <div className="item-card">
                                        <img src= {discount_picture_2} alt="Zdjęcie produktu" />
                                        <div className="item-card_details">
                                            <ul>
                                                <li>{item.itemName}</li>
                                                <li>{item.price} zł</li>
                                            </ul>
                                        </div>
                                    </div>
                                )
                            })}
                            </div>
            </div>
    )
}

export default MyOrders
