import Header from "./Header";
import Footer from "./Footer";

function resetPasswordEmailSent() {
    return(
        <div>
            <Header />
            <section>
                <p>Na podany adres wysłano link umożliwiający zmianę hasła.</p>
            </section>
            <Footer />
        </div>
    )
}

export default resetPasswordEmailSent
