import React from "react";
import discount_picture_1 from "../assets/images/promotion_image_1.jpg";
import discount_picture_2 from "../assets/images/promotion_image_2.jpg";

function Carousel(props) {
    const [carouselElement, setCarouselElement] = React.useState(false)

    return(
        <>
            <img src={discount_picture_1} alt="discount picture"></img>
            <img src={discount_picture_2} alt="discount picture"></img>
            <div className="button__group">
                <button className="switch_disc_img"></button>
                <button className="switch_disc_img"></button>
            </div>
        </>
    )
}

export default Carousel
