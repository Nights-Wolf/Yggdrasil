import React from "react";
import axios from "axios";

const useCheckCart = () => {
    const [cartItemsData, setCartItemsData] = React.useState(null)

    React.useEffect(() => {
        const cart = localStorage.getItem("cart")

        if(cart != null) {
                axios
                    .get("http://localhost:8080/api/cart/itemsQuantity/" + cart)
                    .then(res => {
                        setCartItemsData(res.data)
                    })
                    .catch(err => {
                        console.log(err.response)
                    })
            }
            setCartItemsData(null)
            }, [])

     return [cartItemsData]
}
export default useCheckCart
