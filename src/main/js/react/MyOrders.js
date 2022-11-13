import React from "react";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function MyOrders(props) {

    return(
            <div className="my-Order_card">
                <div className="order-details">
                    <ul>
                        <li>Numer zamówienia: {props.orderNum}</li>
                        <li>Przedmiot: {props.item}</li>
                        <li>Cena: {props.price}</li>
                        <li>Data: {props.date}</li>
                        <li>Status: {props.status}</li>
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
                    <div className="item-card">
                        <img src= {discount_picture_2} alt="Zdjęcie produktu" />
                        <div className="item-card_details">
                            <ul>
                                <li>{props.itemName}</li>
                                <li>{props.itemPrice}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
    )
}

export default MyOrders
