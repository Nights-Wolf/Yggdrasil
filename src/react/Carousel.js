import React from "react";
import discount_picture_1 from "../assets/images/promotion_image_1.jpg";
import discount_picture_2 from "../assets/images/promotion_image_2.jpg";

function Carousel(props) {
    const [carouselElement, setCarouselElement] = React.useState([discount_picture_1, discount_picture_2])
    const [displayCarouselElement, setCarouselElementToDisplay] = React.useState(0)

    const visibleCarouselElementStyle = {
        transform: 'translate(0, 0)'
    }
    const hiddenCarouselElementStyle = {
        transform: 'translate(-999px, 0)'
    }

    function createElements() {
        let elementsToCreate = []
        for(let i = 0; i < carouselElement.length; i++) {
            elementsToCreate.push(<img src={carouselElement[i]} style={i === 0 ? visibleCarouselElementStyle : hiddenCarouselElementStyle} alt="discount picture" aria-hidden={false}></img>)
        }
        return elementsToCreate
    }

    function displayButtons() {
        let buttonsToDisplay = []
        for(let i = 0; i < carouselElement.length; i++) {
            buttonsToDisplay.push(<button className="switch_disc_img" name={i}></button>)
        }
        return buttonsToDisplay
    }

    return(
        <>
            {createElements()}
            <div className="button__group">
                <button id="prev">&#10094;</button>
                {displayButtons()}
                <button id="next">&#10095;</button>
            </div>
        </>
    )
}

export default Carousel
