import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";

function Contact() {

const [data] = useCheckLogin()

    return (
    <div>
       <Header isLogged={data} />
       <div>
        <p>To jest sekcja "O nas" WIP</p>
       </div>
       <Footer />
     </div>
    )
 }
 
 export default Contact
