import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { Link, useParams } from "react-router-dom";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";
import useCheckLogin from "./CheckLogin";

function ProductPage(props) {

const [data] = useCheckLogin()

if(data) {
    console.log("Jestem zalogowany")
} else {
    console.log("nie jestem zalogowany")
}

    const { productId } = useParams()
    const { categoryId } = useParams()
    const { title } = useParams()

    const [visualizedProduct, setVisualizedProduct] = React.useState({
        price: "",
        description: "",
        itemsLeft: ""
    })

     React.useEffect(async () => {
           await axios
            .get("http://localhost:8080/api/item/" + productId)
            .then(res => {
                setVisualizedProduct(res.data)
            })
            .catch(err => {
                console.log(err)
            })
        }, [])

    return(
        <div>
            <Header />
            <section className="product--page">
                <div className="product--details">
                    <img src= {discount_picture_2} />
                    <div className="status">
                        <p className="status--title">{title}</p>
                        <p className="status--price">{visualizedProduct.price}zł</p>
                        <p className="status--availability">Dostępność: {visualizedProduct.itemsLeft > 0 ? "dostępny" : "niedostępny"}</p>
                        <Link to="/products">DODAJ DO KOSZYKA</Link>
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
