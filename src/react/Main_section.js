import React from "react";
import discunt_picture_1 from "../assets/images/promotion_image_1.jpg";

function MainSection() {
    return(
        <section>
            <div className="discounts__container">
                <img src={discunt_picture_1} alt="discount picture"></img>
                <div className="button__group">
                    <button className="switch_disc_img"></button>
                    <button className="switch_disc_img"></button>
                </div>
            </div>
        </section>
    )}

export default MainSection
