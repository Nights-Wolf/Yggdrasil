import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import Cart from "./Cart";

function CartPage() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    const [cartItems, setCartItems] = React.useState([{}])


    React.useEffect(async () => {
        const usersCart = localStorage.getItem("cart")

        if (usersCart) {
            await axios
                .get("http://localhost:8080/api/cart/getItems/" + usersCart)
                .then(res => {
                    setCartItems(res.data)
                })
                .catch(err => {
                    console.log(err.response)
                })
        }
    }, [])

       const cartCards = cartItems.map(cartItems => {return <Cart key={cartItems.id}
                    id={cartItems.id}
                    item= {cartItems.itemId}
                    quantity= {cartItems.quantity} />
        })

    return(
        <div>
            <Header
                isLogged={data}
                cartItems={cartItemsData}
            />
            <section className="cart-section">
            {cartCards}
            </section>
            <Footer />
        </div>
    )
}

export default CartPage
