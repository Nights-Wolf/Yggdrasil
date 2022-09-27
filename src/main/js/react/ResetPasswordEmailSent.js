import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";

function resetPasswordEmailSent() {

const [data] = useCheckLogin()

    return(
        <div>
            <Header isLogged={data} />
            <section>
                <p>Na podany adres wysłano link umożliwiający zmianę hasła.</p>
            </section>
            <Footer />
        </div>
    )
}

export default resetPasswordEmailSent
