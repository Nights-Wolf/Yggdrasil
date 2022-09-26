import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";

function resetPasswordEmailSent() {

const [data] = useCheckLogin()

if(data) {
    console.log("Jestem zalogowany")
} else {
    console.log("nie jestem zalogowany")
}

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
