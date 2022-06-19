import React from "react";
import Carousel from "./Carousel";

function MainSection() {
    

    return(
        <section>
            <div className="discounts__container">
                <Carousel />
            </div>
            <div className="main__section-h2">
            <h2>Promocje</h2>
            </div>
            <div className="news__container">
                <Carousel />
            </div>
        </section>
    )}

export default MainSection
