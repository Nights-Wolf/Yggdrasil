import Header from "./Header";
import Footer from "./Footer";

function About() {
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
