import React from "react";
import axios from "axios";
import useCheckLogin from "./CheckLogin";
import Header from "./Header";
import Footer from "./Footer";
import MyOrders from "./MyOrders";
import useCheckCart from "./CheckCart";

function MyOrdersPage() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()

    const [user, setUser] = React.useState({
        email: ""
    })

    const [order, setOrder] = React.useState([{
        id: "",
        orderValue: "",
        itemId: "",
        itemName: "",
        userId: "",
        userEmail: "",
        orderDate: "",
        street: "",
        zipCode: "",
        city: "",
        voivodeship: "",
        status: ""
    }])

        React.useEffect(() => {
            const accessToken = localStorage.getItem("access_token")

            axios
                .get("http://localhost:8080/api/user/getByToken", {headers: {
                    Authorization: "Bearer " + accessToken}})
                .then(res => {
                     setUser(prevUser => {
                         return {
                            ...prevUser,
                            email: res.data.email,
                     }})
                })
                .catch(err => {
                    navigate("/")
        })
        }, [])

    React.useEffect(async () => {
           await axios
            .get("http://localhost:8080/api/order/getOrder/" + user.email)
            .then(res => {
                setOrder(res.data)
            })
            .catch(err => {
                console.log(err)
            })
    }, [user])

   const orderCards = order.map(order => {return <MyOrders key={order.id}
                orderNum={order.id}
                item={order.itemName}
                price={order.orderValue + " zł"}
                address={order.street + ", " + order.zipCode + " " + order.city + ", " + order.voivodeship}
                date={order.orderDate}
                status={order.status} />
    })

    return(
        <div>
            <Header
             isLogged={data}
             cartItems={cartItemsData}
             />
            <section className="my-orders-section">
                {orderCards}
            </section>
            <Footer />
        </div>
    )
}

export default MyOrdersPage