import Header from "./Header";
import Footer from "./Footer";
import MainSection from "./Main_section";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function App() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

   return (
   <div>
      <Header
       isLogged={data}
       cartItems={cartItemsData}
       />
      <MainSection />
      <Footer />
    </div>
   )
}

export default App
