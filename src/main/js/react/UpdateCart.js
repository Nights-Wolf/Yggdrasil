import  React from "react";
import axios from "axios";

const useUpdateCart = () => {

    const [isCartUpdated, setIsCartUpdated] = React.useState(null)
    const [user, setUser] = React.useState()

    React.useEffect(async() => {
        const cart = localStorage.getItem("cart")
        const accessToken = localStorage.getItem("access_token")

        if(accessToken != null && cart != null) {
            await axios
                .get("http://localhost:8080/api/user/getByToken", {headers: {
                    Authorization: "Bearer " + accessToken}})
                .then(res => {
                     setUser(res.data)
                })
                .catch(err => {
                    console.log(err.response)
        })
        }
    }, [])
console.log(user)
    React.useEffect(async() => {
        const cart = localStorage.getItem("cart")
        const userToken = localStorage.getItem("access_token")

        if(userToken != null && cart != null) {
            await axios
                .put("http://localhost:8080/api/cart/updateOwner/" + cart, user)
                .then(res => {
                    setIsCartUpdated(true)
                })
                .catch(err => {
                    console.log(err.response)
                })
            }
    }, [user])

    return [isCartUpdated]
}
export default useUpdateCart
