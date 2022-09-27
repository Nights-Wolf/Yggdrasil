import React from "react";
import axios from "axios";

const useCheckLogin = () => {
    const [data, setData] = React.useState(null)

    React.useEffect(() => {
        const refreshToken = localStorage.getItem("refresh_token")

        if(refreshToken != null) {
            axios
                .get("http://localhost:8080/api/authentication/refresh/token", {headers: {
                Authorization: "Bearer " + refreshToken}})
                .then(res => {
                    const accessToken = res.headers.access_token
                    localStorage.setItem('access_token', accessToken)
                    setData(true)
                })
                .catch(err => {
                    console.log(err.response)
                })
            }
            setData(false)
            }, [])
     return [data]
}
export default useCheckLogin
