import React from "react";
import discount_picture_1 from "./assets/images/promotion_image_1.jpg";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function Carousel(props) {
    const [carouselElement, setCarouselElement] = React.useState([{
        id: 1,
        url: discount_picture_1
    },
    {
        id: 2,
        url: discount_picture_2
    }])

    const [carouselElementToDisplay, setCarouselElementToDisplay] = React.useState({
        url: carouselElement[0].url,
        visible: true
    })

    const visibleCarouselElementStyle = {
        transform: 'translate(0, 0)'
    }

    const hiddenCarouselElementStyle = {
        transform: 'translate(-999px, 0)'
    }

    const buttonActive = {
        background: '#0F9F49'
    }

    const triggerClick = React.useRef([])

    function clickButton(elementNum) {
        triggerClick.current[elementNum].click()
    }

    React.useEffect(() => {
      let elementNum = 0
      const interval = setInterval(() => {
      if(elementNum >= carouselElement.length - 1) {
        elementNum = 0
      } else {
        elementNum = elementNum + 1
      }
        clickButton(elementNum)
      }, 10000)
      return () => clearInterval(interval)
    }, [])

    function carouselNextElement(event) {
        const {name, value} = event.target
        setCarouselElementToDisplay(prevCarouselElementToDisplay => {
          return {  ...prevCarouselElementToDisplay,
            url: value,
        }})
    }

    return(
        <>
            <img src={carouselElementToDisplay.url} style={carouselElementToDisplay.visible ? visibleCarouselElementStyle : hiddenCarouselElementStyle} alt="discount picture" aria-hidden={false}></img>
            <div className="button__group">
                {carouselElement.map(carouselsElement => {
                    return (
                        <button ref={(element) => triggerClick.current.push(element)} style={carouselElementToDisplay.url === carouselsElement.url ? buttonActive : null} className="switch_disc_img" id={carouselsElement.id} name={carouselsElement.id} value={carouselsElement.url} onClick={carouselNextElement} aria-label="Wybierz element karuzeli"></button>
                )})}
            </div>
        </>
    )
}

export default Carousel
