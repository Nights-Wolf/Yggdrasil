import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import Card from "./Card";
import Pagination from "./Pagination";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";
import {useNavigate} from 'react-router-dom';

function Products() {
    const navigate = useNavigate();
    const [filterData, setFilterData] = React.useState({
        price: "",
        availability: ""
    })

    function handleFilterChange(event) {
        setFilterData(prevFormData => {
            return {
            ...prevFormData,
            [event.target.name]: event.target.value
            }
        })
    }


    React.useEffect(() => {
        navigate("./price=" + filterData.price + "?" + "availability=" + filterData.availability, { replace: true })
}, [filterData])

    const [product, setProduct] = React.useState([{
        id: "",
        itemName: "",
        image: "",
        created: "",
        categoryId: "",
        price: "",
        itemsLeft: ""
    }])

    React.useEffect(async () => {
       await axios
        .get("http://localhost:8080/api/item/all")
        .then(res => {
            setProduct(res.data)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])

         const productCard = product.map(product => {
                return <Card img={product.image}
                id= {product.id}
                category= {product.categoryId}
                title= {product.itemName}
                price= {product.price} />
                })


    return (
    <div>
       <Header />
       <section className="products-section">
        <div className="filters-container">
            <div className="filters-container__price-filter">
                <form>
                    <label htmlFor="price">Cena: </label>
                    <select name="price" value={filterData.price} onChange={handleFilterChange}>
                        <option value="">---Wybierz---</option>
                        <option value="1">rosnąco</option>
                        <option value="2">malejąco</option>
                    </select>
                </form>
            </div>
            <div className="filters-container__availability-filter">
                <form>
                    <label htmlFor="availability">Dostępność: </label>
                    <select name="availability" value={filterData.availability} onChange={handleFilterChange}>
                        <option value="" default>---Wybierz---</option>
                        <option value="1">gotowe do wysyłki</option>
                        <option value="2">niedostępne</option>
                    </select>
                </form>
            </div>
        </div>
        <div className="products-container">
            {productCard}
        </div>
        <Pagination />
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Products
