import React from "react";
import axios from "axios";
import useCheckLogin from "./CheckLogin";
import Header from "./Header";
import Footer from "./Footer";
import MyOrders from "./MyOrders";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function MyOrdersPage() {

    const [data] = useCheckLogin()
    const [cartItemsData] = useCheckCart()
    const [isCartUpdated] = useUpdateCart()

    const [user, setUser] = React.useState({
        email: ""
    })

    const [order, setOrder] = React.useState([{}])
    const [shipment, setShipment] = React.useState([{}])
    const [payment, setPayment] = React.useState([{}])

        React.useEffect(async () => {
            const accessToken = localStorage.getItem("access_token")

           await axios
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

   React.useEffect(() => {
        order.forEach(orders => {
         axios
            .get("http://localhost:8080/api/shipments/" + orders.id)
            .then(res => {
                setShipment(res.data)
            })
            .catch(err => {
                console.log(err.response)
            })
         axios
            .get("http://localhost:8080/api/payment/" + orders.id)
            .then(res => {
                setPayment(res.data)
            })
            .catch(err => {
                console.log(err.response)
            })
            })
    }, [order])

   const orderCards = order.map(order => {return <MyOrders key={order.id}
                orderNum={order.id}
                username={order.username}
                surname={order.surname}
                price={order.orderValue + " zł"}
                street={order.street}
                zipCode={order.zipCode}
                city={order.city}
                voivodeship={order.voivodeship}
                date={order.orderDate}
                status={order.status}
                payment={payment.name}
                shipment={shipment.name}
                />
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
