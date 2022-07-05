import Header from "./Header";
import Footer from "./Footer";
import { Link } from "react-router-dom";

function ProductPage(props) {
    return(
        <div>
            <Header />
            <section className="product--page">
                <div className="product--details">
                    <img/>
                    <div className="status">
                        <p></p>
                        <p></p>
                        <Link to="/products"></Link>
                    </div>
                </div>
                <div>
                    <p></p>
                </div>
            </section>
            <Footer />
        </div>
    )

}

export default ProductPage
