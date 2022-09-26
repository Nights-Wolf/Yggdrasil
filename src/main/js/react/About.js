import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";

function About() {

const [data] = useCheckLogin()

if(data) {
    console.log("Jestem zalogowany")
} else {
    console.log("nie jestem zalogowany")
}

    return (
    <div>
       <Header />
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
