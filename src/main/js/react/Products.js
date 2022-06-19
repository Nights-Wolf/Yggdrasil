import Header from "./Header";
import Footer from "./Footer";
import Card from "./Card";
import Pagination from "./Pagination";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function Products() {
    return (
    <div>
       <Header />
       <section>
        <div className="filters-container">
            <div className="filters-container__price-filter">
                <span>Cena: </span><span>rosnąco</span>
            </div>
            <div className="filters-container__availability-filter">
                <span>Dostępność: </span><span>Gotowe do wysyłki</span>
            </div>
        </div>
        <div className="products-container">
            <Card 
            img={discount_picture_2}
            title="Bursztynowe drzewko"
            price="300,99"/>
            <Card 
            title="Piękne drzewko"
            price="300.99"/>
            <Card 
            title="Piękne drzewko"
            price="300"/>
            <Card 
            title="Piękne drzewko"
            price="300"/>
            <Card 
            title="Piękne drzewko"
            price="300"/>
            <Card 
            title="Piękne drzewko"
            price="300"/>
        </div>
        <Pagination />
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Products