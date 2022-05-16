import React from "react";
import discount_picture_1 from "../assets/images/promotion_image_1.jpg";
import discount_picture_2 from "../assets/images/promotion_image_2.jpg";

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
    
    function displayElements() {
        let elementsToDisplay = []
        for(let i = 0; i < carouselElement.length; i++) {
            elementsToDisplay.push(<img src={carouselElementToDisplay[i].url} style={carouselElementToDisplay[i].visible ? visibleCarouselElementStyle : hiddenCarouselElementStyle} alt="discount picture" aria-hidden={false}></img>)
        }
        return elementsToDisplay
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
        let elementToDisplay
        let newArr = [...carouselElementToDisplay]

       for(let i = 0; i < carouselElementToDisplay.length; i++) {
            if (carouselElementToDisplay[i].visible) {
                visibleElement = carouselElementToDisplay[i].id
            }
        };

        if(elementToDisplay > carouselElementToDisplay.length) {
            elementToDisplay = 1;
        } else {
        elementToDisplay = visibleElement + 1
        }
        
        for(let i = 0; i < carouselElementToDisplay.length; i++) {
               if (!carouselElementToDisplay[i].id === elementToDisplay && carouselElementToDisplay[i].visible) {
                    newArr[i] = {
                        ...newArr,
                        visible: false
                    }
                   setCarouselElementToDisplay(newArr)
               }
            }

            for(let i = 0; i < carouselElementToDisplay.length; i++) {
               if (carouselElementToDisplay[i].id === elementToDisplay && !carouselElementToDisplay[i].visible) {
                    newArr[i] = {
                        ...newArr,
                        visible: true
                    }
                   setCarouselElementToDisplay(newArr[i])
                  
               }              
           }
    }

    return(
        <>
            {carouselElementToDisplay.map((element) => (
                <img src={element.url} style={element.visible ? visibleCarouselElementStyle : hiddenCarouselElementStyle} alt="discount picture" aria-hidden={false}></img>
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
