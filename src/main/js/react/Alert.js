import React from "react";

function Alert(props) {

    const [isVisible, setIsVisible] = React.useState(false)
    const visibility = props.isVisible

    React.useEffect(() => {
        setIsVisible(visibility)
    }, [visibility])

    const alertVisStyle = {
        display: isVisible ? "block" : "none"
    }

    function changeVisibility() {
        setIsVisible(false)
    }

    return(
        <div style={alertVisStyle} className="alert_container">
            <h2>{props.alertText}</h2>
            <button onClick={changeVisibility}>X</button>
        </div>

    )

}

export default Alert
