import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";
import Cart from "./Cart";
import { Link } from "react-router-dom";

function CartPage() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

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

       const cartCards = cartItems.map(cartItem => {
       return <Cart key={cartItem.id}
                    id={cartItem.id}
                    quantity= {cartItem.quantity} />
        })

    return(
        <div>
            <Header
                isLogged={data}
                cartItems={cartItemsData}
            />
            <section className="cart-section">
            {cartItemsData ? <>
            {cartCards}
            <div className="cart-submit-buttons">
                <Link to="/products">Kontynuuj zakupy</Link>
                <Link to= "/order">Złóż zamówienie</Link>
            </div></>
             : <h1>Twój koszyk jest pusty!</h1>}
            </section>
            <Footer />
        </div>
    )
}

export default CartPage
