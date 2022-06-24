import React from "react";
import { Link } from "react-router-dom";

function Card(props) {
    return(
        <div className="card">
            <Link to="/about"><img src={props.img} alt="cardimage"/></Link>
            <div className="card-stats">
                <p>{props.title}</p>
                <span> {props.price} zł</span>
            </div>
        </div>
    )
}

export default Card
