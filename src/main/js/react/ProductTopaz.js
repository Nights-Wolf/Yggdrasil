import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import Card from "./Card";
import Pagination from "./Pagination";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";
import { useNavigate, useParams } from 'react-router-dom';
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function ProductTopaz() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()
    const [currentPage, setCurrentPage] = React.useState(1)
    const [productsPerPage, setProductsPerPage] = React.useState(20)

    const [filterData, setFilterData] = React.useState({
        price: "0",
        availability: "0"
    })

    const [product, setProduct] = React.useState([{
        id: "",
        itemName: "",
        image: "",
        created: "",
        categoryId: "",
        price: "",
        itemsLeft: ""
    }])

    function handleFilterChange(event) {
        setFilterData(prevFormData => {
            return {
            ...prevFormData,
            [event.target.name]: event.target.value
            }
        })
    }

    React.useEffect(async () => {
       await axios
        .get("http://localhost:8080/api/item/category/3")
        .then(res => {
            setProduct(res.data)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])

    if(filterData.price == 2) {
        product.sort((a, b) => parseFloat(b.price) - parseFloat(a.price))
    } else if(filterData.price == 1) {
        product.sort((a, b) => parseFloat(a.price) - parseFloat(b.price))
    } else if(filterData.price == 0) {
       product.sort((a, b) => parseFloat(a.id) - parseFloat(b.id))
    }

    const indexOfLastPost = currentPage * productsPerPage
    const indexOfFirstPost = indexOfLastPost - productsPerPage
    const currentProducts = product.slice(indexOfFirstPost, indexOfLastPost)

   const card = currentProducts.map(product => { return <Card key={product.id}
        img={product.image}
        id= {product.id}
        category= {product.categoryId}
        title= {product.itemName}
        price= {product.price}
        itemsLeft = {product.itemsLeft} />
    })

    const paginate = pageNumber => setCurrentPage(pageNumber)

    const filteredCards =  product.filter(product => product.itemsLeft > 0)
    const unavailableCards = filteredCards.map(filteredCards => {
     return <Card key={product.id}
        img={filteredCards.image}
        id= {filteredCards.id}
        title= {filteredCards.itemName}
        price= {filteredCards.price}
        itemsLeft = {filteredCards.itemsLeft} />
     })

    return (
    <div>
       <Header
        isLogged={data}
        cartItems={cartItemsData}
        />
       <section className="products-section">
        <div className="filters-container">
            <div className="filters-container__price-filter">
                <form>
                    <label htmlFor="price">Cena: </label>
                    <select name="price" value={filterData.price} onChange={handleFilterChange}>
                        <option value="0">---Wybierz---</option>
                        <option value="1">rosnąco</option>
                        <option value="2">malejąco</option>
                    </select>
                </form>
            </div>
            <div className="filters-container__availability-filter">
                <form>
                    <label htmlFor="availability">Dostępność: </label>
                    <select name="availability" value={filterData.availability} onChange={handleFilterChange}>
                        <option value="0">---Wybierz---</option>
                        <option value="1">gotowe do wysyłki</option>
                        <option value="2">niedostępne</option>
                    </select>
                </form>
            </div>
        </div>
        <div className="products-container">
            {filterData.availability == 2 ? unavailableCards : card}
        </div>
        <Pagination
            productsPerPage={productsPerPage}
            totalProducts={product.length}
            paginate={paginate}
          />
       </section>
       <Footer />
     </div>
    )
 }

 export default ProductTopaz
