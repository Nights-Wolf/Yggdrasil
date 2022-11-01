import React from "react";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function MyOrders(props) {

    return(
        <div className="my-Order_card">
            <img src= {discount_picture_2} />
            <div className="order-details">
                <p>Numer zamówienia: {props.orderNum}</p>
                <p>Przedmiot: {props.item}</p>
                <p>Cena: {props.price}</p>
                <p>Adres: {props.address}</p>
                <p>Data: {props.date}</p>
                <p>Status: {props.status}</p>
            </div>
        </div>
    )
}

export default MyOrders
