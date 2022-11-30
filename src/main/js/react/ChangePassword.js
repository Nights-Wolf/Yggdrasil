import React from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { useNavigate } from "react-router-dom";
import useCheckLogin from "./CheckLogin";
import useCheckCart from "./CheckCart";
import useUpdateCart from "./UpdateCart";

function ChangePassword() {

const [data] = useCheckLogin()
const [cartItemsData] = useCheckCart()
const [isCartUpdated] = useUpdateCart()

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

 const [changePassword, setChangePassword] = React.useState({
        oldPassword: "",
        newPassword: ""
    })

    const [passwordChecker, setPasswordChecker] = React.useState({
        repeatNewPassword: ""
    })

    const [error, setError] = React.useState({
        oldPassword: "",
        newPassword: "",
        repeatNewPassword: ""
    })

    const errorVisible = {
        color: "red"
    }

    const errorInvisible = {
        color: "default"
    }

    function handleChange(event) {
        const {name, value} = event.target
        setChangePassword(prevChangePassword => {
            return {
                ...prevChangePassword,
                [name]: value
            }
        })
    }

    function validatePassword(event) {
        const {name, value} = event.target
        setPasswordChecker(prevPasswordChecker => {
            return {
                ...prevPasswordChecker,
                [name] : value
            }
        })
    }

        function checkNewPassword(passwordChecker) {
             if (passwordChecker != changePassword.newPassword) {
                setError(prevError => {
                    return {
                        ...prevError,
                        repeatNewPassword: "Wpisz poprawne hasło!"
                    }
                })
                return false
             }
             setError(prevError => {
                return {
                    ...prevError,
                    repeatNewPassword: ""
                }
             })
             return true
            }

    function checkPasswordLength(password) {
        if (password.length <= 5 || password == "") {
            setError(prevError => {
                return {
                    ...prevError,
                    newPassword: "Hasło musi zawierać co najmnniej 6 znaków!"
                }
            })
            return false
        }
        setError(prevError => {
            return {
                ...prevError,
                newPassword: ""
            }
        })
        return true
    }

    const handleSubmit = event => {
            event.preventDefault()

            const isNewPasswordCorrect = checkNewPassword(passwordChecker.repeatNewPassword)
            const isPasswordLengthCorrect = checkPasswordLength(changePassword.newPassword)

            if(isNewPasswordCorrect && isPasswordLengthCorrect) {
                axios
                    .put("http://localhost:8080/api/user/changePassword", {
                        email: user.email,
                        password: changePassword.oldPassword,
                        newPassword: changePassword.newPassword
                    })
                    .then(res => {

                        window.location.reload(false)
                    })
                    .catch(err => {
                        if (err.response.status === 403) {
                            setError(prevError => {
                                return {
                                    ...prevError,
                                    oldPassword: "Hasło nie pasuje do obecnego hasła!"
                                }
                            })
                     } else if (err.response.status === 417){
                        setError(prevError => {
                             return {
                                ...prevError,
                                newPassword: "Nowe hasło nie może być starym hasłem!"
                                }
                            })
                     }
                })
            }
          }

    return (
    <div>
       <Header
        isLogged={data}
        cartItems={cartItemsData}
         />
       <section className="remindPassword-section">
        <form onSubmit={handleSubmit}>
            <input type="password" style={error.oldPassword === "" ? errorInvisible : errorVisible} placeholder={error.oldPassword === "" ? "Stare hasło" : error.oldPassword} id="oldPassword"  name="oldPassword"  onChange={handleChange}/>
            <input type="password" style={error.newPassword === "" ? errorInvisible : errorVisible} placeholder={error.newPassword === "" ? "Nowe hasło" : error.newPassword} id="newPassword"  name="newPassword"  onChange={handleChange}/>
            <input type="password" style={error.repeatNewPassword === "" ? errorInvisible : errorVisible} placeholder={error.repeatNewPassword === "" ? "Powtórz hasło" : error.repeatNewPassword} id="repeatNewPassword"  name="repeatNewPassword" onChange={validatePassword}/>
            <button>Zmień hasło</button>
        </form>
       </section>
       <Footer />
     </div>
    )
}

export default ChangePassword
