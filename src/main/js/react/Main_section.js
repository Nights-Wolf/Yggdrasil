import React from "react";
import Carousel from "./Carousel";

function MainSection() {
    

    return(
        <section className="main-section">
            <div className="discounts__container">
                <Carousel />
            </div>
            <div className="news__container">
                <Carousel />
            </div>
        </section>
    )}

export default MainSection
