import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";

function Rodo() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    return (
    <div>
       <Header
        isLogged={data}
        cartItems={cartItemsData}
         />
       <div>
        <p>To jest sekcja "O nas" WIP</p>
       </div>
       <Footer />
     </div>
    )
 }
 
 export default Rodo
