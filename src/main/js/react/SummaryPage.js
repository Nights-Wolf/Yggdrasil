import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";
import Summary from "./Summary";

function SummaryPage() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

    return (
        <div>
            <Header
             isLogged={data}
             cartItems={cartItemsData}
             />
            <section className="summary-section">
                <Summary />
            </section>
            <Footer />
        </div>
    )
}

export default SummaryPage
