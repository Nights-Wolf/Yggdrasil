import React from "react";
import Carousel from "./Carousel";

function MainSection() {
    

    return(
        <section className="main-section">
            <h1>Drzewo Światów takie jakie chcesz!</h1>
            <div className="discounts__container">
                <Carousel />
            </div>
            <h1>Czym jest Yggdrasil?</h1>
            <p>Yggdrasil nawiązujący do nordyckiego "Drzewa świata" jest pomysłem zainspirowanym twórczością pewnego znanego mi jegomościa,
            parającego się sztuką przekuwania drewno w atrakcyjne i pomysłowe dekoracje, które w niecodzienny sposób umilą Państwu Fimbulvinter i następujący po nim Ragnarok.</p>
        </section>
    )}

export default MainSection
