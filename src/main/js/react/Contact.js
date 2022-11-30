import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function Contact() {

const [data] = useCheckLogin()
const [cartItemsData] = useCheckCart()
const [isCartUpdated] = useUpdateCart()

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
 
 export default Contact
