import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";

function Terms() {

const [data] = useCheckLogin()

if(data) {
    console.log("Jestem zalogowany")
} else {
    console.log("nie jestem zalogowany")
}

    return (
    <div>
       <Header />
       <div>
        <p>To jest sekcja "O nas" WIP</p>
       </div>
       <Footer />
     </div>
    )
 }
 
 export default Terms
