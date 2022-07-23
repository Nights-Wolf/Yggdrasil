import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import Card from "./Card";
import Pagination from "./Pagination";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";
import { useParams } from "react-router-dom";

function Products() {
    let { categoryId, productId } = useParams()

    const [product, setProduct] = React.useState([{
        itemName: "",
        created: "",
        categoryId: "",
        price: "",
        itemsLeft: ""
    }])

    React.useEffect(async () => {
        axios
        .get("http://localhost:8080/api/item/all", {
        })
        .then(res => {
            setProduct(res.data)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])
console.log(product)
    return (
    <div>
       <Header />
       <section className="products-section">
        <div className="filters-container">
            <div className="filters-container__price-filter">
                <form>
                    <label htmlFor="price">Cena: </label>
                    <select name="price">
                        <option value="rosnąco" default>rosnąco</option>
                        <option value="malejąco">malejąco</option>
                    </select>
                </form>
            </div>
            <div className="filters-container__availability-filter">
                <form>
                    <label htmlFor="availability">Dostępność: </label>
                    <select name="availability">
                        <option value="gotowe do wysyłki" default>gotowe do wysyłki</option>
                        <option value="niedostępne">niedostępne</option>
                    </select>
                </form>
            </div>
        </div>
        <div className="products-container">
            <Card
            img={discount_picture_2}
            link="1/1"
            title= {product.itemName}
            price= {product.price}/>
            <Card
            link="1/1"
            title="Piękne drzewko"
            price="300.99"/>
        </div>
        <Pagination />
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Products