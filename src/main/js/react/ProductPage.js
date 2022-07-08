import Header from "./Header";
import Footer from "./Footer";
import { Link } from "react-router-dom";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function ProductPage(props) {
    return(
        <div>
            <Header />
            <section className="product--page">
                <div className="product--details">
                    <img src= {discount_picture_2} />
                    <div className="status">
                        <p className="status--title">Bursztynowe drzewko najpięmkniejsze</p>
                        <p className="status--availability">Dostępność: dostępny</p>
                        <Link to="/products">DODAJ DO KOSZYKA</Link>
                    </div>
                </div>
                <div className="description">
                    <p>OPIS PRODUKTU</p>
                </div>
            </section>
            <Footer />
        </div>
    )

}

export default ProductPage
