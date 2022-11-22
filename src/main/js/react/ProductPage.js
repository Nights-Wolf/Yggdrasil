import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useParams, useNavigate } from "react-router-dom";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";

function ProductPage(props) {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    const navigate = useNavigate()

    const { productId } = useParams()
    const { categoryId } = useParams()
    const { title } = useParams()

    const [user, setUser] = React.useState()

    const [itemCount, setItemCount] = React.useState(1)

    const [visualizedProduct, setVisualizedProduct] = React.useState({
        price: "",
        description: "",
        itemsLeft: ""
    })

    React.useEffect(() => {
        const accessToken = localStorage.getItem("access_token")

         axios
            .get("http://localhost:8080/api/user/getByToken", {headers: {
                Authorization: "Bearer " + accessToken}})
            .then(res => {
                setUser(res.data)
                })
            .catch(err => {
                console.log(err)
            })
    }, [])

    function handleChange(event) {
        setItemCount(event.target.value)
    }

    function adjustCount(amount) {
        if (itemCount || itemCount != 0) {
            setItemCount(amount + parseInt(itemCount, 10))
        } else {
            setItemCount(1)
        }
    }

     React.useEffect(() => {
           axios
            .get("http://localhost:8080/api/item/" + productId)
            .then(res => {
                setVisualizedProduct(res.data)
            })
            .catch(err => {
                console.log(err.response)
            })
        }, [])

        const addToCart = async event => {
            event.preventDefault()

            const usersCart = localStorage.getItem("cart")

            if (usersCart) {
                if (itemCount > 0) {
                const date = new Date(Date.now())

                const cartItem = {
                    itemId: visualizedProduct,
                    quantity: itemCount,
                    createdDate: date,
                }

                axios
                    .post("http://localhost:8080/api/cart/addItem/" + usersCart, cartItem)
                    .then(res => {
                        navigate("/cart")
                    })
                    .catch(err =>
                        console.log(err.response)
                    )
                }
            } else {
              if (itemCount > 0) {
                const uuid = require('uuid')
                const cartToken = uuid.v1()
                const date = new Date(Date.now())

                const cart = {
                    userId: user,
                    createdDate: date,
                    token: cartToken
                }

              await axios
                    .post("http://localhost:8080/api/cart/create", cart)
                    .then(res => {
                        const cartToken = res.headers.cart
                        localStorage.setItem('cart', cartToken)
                    })
                    .catch(err => {
                        console.log(err.response)
                    })

                const cartItem = {
                    itemId: visualizedProduct,
                    quantity: itemCount,
                    createdDate: date,
                }

                axios
                    .post("http://localhost:8080/api/cart/addItem/" + cartToken, cartItem)
                    .then(res => {
                        navigate("/cart")
                    })
                    .catch(err =>
                        console.log(err.response)
                    )
            }
         }
        }

    return(
        <div>
            <Header
            isLogged={data}
            cartItems={cartItemsData}
             />
            <section className="product--page">
                <div className="product--details">
                    <img src= {discount_picture_2} />
                    <div className="status">
                        <p className="status--title">{title}</p>
                        <p className="status--price">{visualizedProduct.price}zł</p>
                        <p className="status--availability">Dostępność: {visualizedProduct.itemsLeft > 0 ? "dostępny" : "niedostępny"}</p>
                        <form onSubmit={addToCart}>
                            <div className="item-quantity">
                                <button type="button" name="add" onClick={() => adjustCount(1)} >+</button>
                                <input type="number" name="quantity"  value={itemCount} onChange={handleChange} />
                                <button type="button" name="subtract" onClick={() => adjustCount(-1)} >-</button>
                            </div>
                        <button>DODAJ DO KOSZYKA</button>
                        </form>
                    </div>
                </div>
                <div className="description">
                    <p>{visualizedProduct.description}</p>
                </div>
            </section>
            <Footer />
        </div>
    )

}

export default ProductPage
