import React from "react";
import { Link } from "react-router-dom";

function Card(props) {
    let link = "/product/" + props.id + "/" + props.category + "/" + props.title

    return(
        <div className="card">
            <Link to={link}><img src={props.img} alt="product image"/></Link>
            <div className="card-stats">
                <p>{props.title}</p>
                <span> {props.price} zł</span>
            </div>
        </div>
    )
}

export default Card
