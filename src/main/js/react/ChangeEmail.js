import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";

function ChangeEmail() {

const [data] = useCheckLogin()

const navigate = useNavigate()

    const [user, setUser] = React.useState({
        email: "",
    })

 React.useEffect(async () => {
    const accessToken = localStorage.getItem("access_token")

           await axios
            .get("http://localhost:8080/api/user/getByToken", {headers: {
                Authorization: "Bearer " + accessToken
            }})
            .then(res => {
                setUser(prevUser => {
                    return {
                        ...prevUser,
                        email: res.data.email
                    }
                })
            })
            .catch(err => {
                navigate("/")
            })
        }, [])

 const [changeEmail, setChangeEmail] = React.useState({
        newEmail: ""
    })

    const [error, setError] = React.useState({
        newEmail: "",
    })

    const errorVisible = {
        color: "red"
    }

    const errorInvisible = {
        color: "default"
    }

    function handleChange(event) {
        const {name, value} = event.target
        setChangeEmail(prevChangeEmail => {
            return {
                ...prevChangeEmail,
                [name]: value
            }
        })
    }

        function emailChecker(email) {
             if (email == user.email) {
                setError(prevError => {
                    return {
                        ...prevError,
                        newEmail: "Nowy email nie może być taki sam jak poprzedni!"
                    }
                })
                return false
             }
             setError(prevError => {
                return {
                    ...prevError,
                    newEmail: ""
                }
             })
             return true
            }

    const handleSubmit = event => {
            event.preventDefault()

            const isEmailCorrect = emailChecker(changeEmail.newEmail)

            if(isEmailCorrect) {
                axios
                    .put("http://localhost:8080/api/user/changeEmail", {
                        email: user.email,
                        newEmail: changeEmail.newEmail
                    })
                    .then(res => {
                        localStorage.removeItem("access_token")
                        localStorage.removeItem("refresh_token")

                        navigate("/login")
                    })
                    .catch(err => {
                        if (err.response.status === 409) {
                            setError(prevError => {
                                return {
                                    ...prevError,
                                    newEmail: "Email jest już używany!"
                                }
                            })
                     } else {
                        console.log(err)
                     }
                })
            }
          }

    return (
    <div>
       <Header isLogged={data} />
       <section className="remindPassword-section">
        <form onSubmit={handleSubmit}>
            <input type="email" style={error.newEmail === "" ? errorInvisible : errorVisible} placeholder={error.newEmail === "" ? "Nowy email" : error.newEmail} id="newEmail"  name="newEmail"  onChange={handleChange}/>
            <button>Zmień email</button>
        </form>
       </section>
       <Footer />
     </div>
    )
}

export default ChangeEmail
