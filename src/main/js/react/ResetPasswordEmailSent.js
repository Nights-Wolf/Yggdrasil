import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";

function resetPasswordEmailSent() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    return(
        <div>
            <Header
             isLogged={data}
             cartItems={cartItemsData}
              />
            <section>
                <p>Na podany adres wysłano link umożliwiający zmianę hasła.</p>
            </section>
            <Footer />
        </div>
    )
}

export default resetPasswordEmailSent
