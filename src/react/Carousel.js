import React from "react";
import discount_picture_1 from "../assets/images/promotion_image_1.jpg";
import discount_picture_2 from "../assets/images/promotion_image_2.jpg";

function Carousel(props) {
    const [carouselElement, setCarouselElement] = React.useState([discount_picture_1, discount_picture_2])
    const [displayCarouselElement, setCarouselElementToDisplay] = React.useState()

    const visibleCarouselElementStyle = {
        transform: 'translate(0, 0)'
    }
    const hiddenCarouselElementStyle = {
        transform: 'translate(-999px, 0)'
    }

    function displayElements() {
        let elementsToDisplay = []
        for(let i = 0; i < carouselElement.length; i++) {
            elementsToDisplay.push(<img src={carouselElement[i]} style={i === 0 ? visibleCarouselElementStyle : hiddenCarouselElementStyle} alt="discount picture"></img>)
        }
        return(elementsToDisplay)
    }

    return(
        <>
            {displayElements()}
            <div className="button__group">
                <button className="switch_disc_img"></button>
                <button className="switch_disc_img"></button>
            </div>
        </>
    )
}

export default Carousel
