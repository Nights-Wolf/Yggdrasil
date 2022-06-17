import React from "react";

function Card(props) {
    return(
        <div className="card">
            <img alt="cardimage"/>
            <p>{props.title}</p>
            <div className="card-stats">
                <span className="gray"><i class="fa fa-star"></i></span>
                <span className="gray"> {props.price} zł</span>
            </div>
        </div>
    )
}

export default Card
