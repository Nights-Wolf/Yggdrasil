import React from "react";
import discount_picture_1 from "./assets/images/promotion_image_1.jpg";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function Carousel(props) {
    const [carouselElement, setCarouselElement] = React.useState([discount_picture_1, discount_picture_2])

    const [carouselElementToDisplay, setCarouselElementToDisplay] = React.useState([{
        id: 1,
        url: carouselElement[0],
        visible: true
    },
      { 
        id: 2,
        url: carouselElement[1],
        visible: false
    }])

    const visibleCarouselElementStyle = {
        transform: 'translate(0, 0)'
    }

    const hiddenCarouselElementStyle = {
        transform: 'translate(-999px, 0)'
    }

    function displayIndicators() {
        let indicatorsToDisplay = []
        for(let i = 0; i < carouselElement.length; i++) {
            indicatorsToDisplay.push(<span className="switch_disc_img" name={i}></span>)
        }
        return indicatorsToDisplay
    }

    function carouselNextElement() {
        let visibleElement
        let elementToDisplay = 0
        let newArr = []

        newArr = carouselElementToDisplay.map((element) => {
            console.log(element.visible)
            if (element.visible) {
                visibleElement = element.id
            }

            if(elementToDisplay > carouselElementToDisplay.length) {
                elementToDisplay = 1;
            } else {
            elementToDisplay = visibleElement + 1
            }
            if(element.id === elementToDisplay)
               return element.visible[elementToDisplay - 1] = false
        })
        setCarouselElementToDisplay(newArr)
    }

    return(
        <>
            {carouselElementToDisplay.map((element) => (
                <img src={element.url} style={element.visible ? visibleCarouselElementStyle : hiddenCarouselElementStyle} alt="discount picture" aria-hidden={false} key={element.id}></img>
               ))}
            <div className="button__group">
                <button id="prev">&#10094;</button>
                {displayIndicators()}
                <button id="next" onClick={carouselNextElement}>&#10095;</button>
            </div>
        </>
    )
}

export default Carousel
