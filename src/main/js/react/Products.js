import Header from "./Header";
import Footer from "./Footer";
import Card from "./Card";
import Pagination from "./Pagination";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";

function Products() {
    return (
    <div>
       <Header />
       <section className="products-section">
        <div className="filters-container">
            <div className="filters-container__price-filter">
                <form>
                    <label for="price">Cena: </label>
                    <select name="price">
                        <option value="rosnąco" selected>rosnąco</option>
                        <option value="malejąco">malejąco</option>
                    </select>
                </form>
            </div>
            <div className="filters-container__availability-filter">
                <form>
                    <label for="availability">Dostępność: </label>
                    <select name="availability">
                        <option value="gotowe do wysyłki" selected>gotowe do wysyłki</option>
                        <option value="niedostępne">niedostępne</option>
                    </select>
                </form>
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