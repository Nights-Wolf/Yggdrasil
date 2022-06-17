import Header from "./Header";
import Footer from "./Footer";
import Card from "./Card"

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
       </section>
       <Footer />
     </div>
    )
 }
 
 export default Products