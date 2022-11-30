import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function About() {

const [data] = useCheckLogin()
const [cartItemsData] = useCheckCart()
const [isCartUpdated] = useUpdateCart()

    return (
    <div>
       <Header
       isLogged={data}
       cartItems={cartItemsData}
       />
       <section className="about-page">
        <article>
         <h1>Nasza działalność</h1>
         <p>Nasze drzewka powstają tylko z połączenia kawałków drewna znalezionych w pobliskim lesie oraz specjalnie zamówionych kamieni. Każde drzewko jest wyjątkowe.</p>
        </article>
       </section>
       <Footer />
     </div>
    )
 }
 
 export default About
