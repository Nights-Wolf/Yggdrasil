import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { createBrowserHistory } from "history";
import App from "./react/App";
import About from "./react/About";
import Terms from "./react/Terms";
import Contact from "./react/Contact";
import Rodo from "./react/RODO";
import Register from "./react/Register";
import Login from "./react/Login";
import Products from "./react/Products";
import ProductPage from "./react/ProductPage";
import ProductAmber from "./react/ProductAmber";
import ProductTopaz from "./react/ProductTopaz";
import ProductAmethyst from "./react/ProductAmethyst";
import ProductLapiz from "./react/ProductLapiz";
import ProductQuartz from "./react/ProductQuartz";
import ProductMntGem from "./react/ProductMntGem";
import RemindPassword from "./react/RemindPassword"
import ForgotPassword from "./react/ForgotPassword";
import EditProfile from "./react/EditProfile";
import ChangePassword from "./react/ChangePassword";
import ResetPasswordEmailSent from "./react/ResetPasswordEmailSent";
import Error from "./react/ErrorPage";
import PassResetTokenExpire from "./react/PassResetTokenExpire";
import PassResetTokenNotFound from "./react/PassResetTokenNotFound";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter history={createBrowserHistory}>
            <Routes>           
                <Route path="/" element={<App />} />
                <Route path="/about" element={<About />} />
                <Route path="/terms" element={<Terms />} />
                <Route path="/contact" element={<Contact />} />
                <Route path="/rodo" element={<Rodo />} />
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
                <Route path="/editProfile" element={<EditProfile />} />
                <Route path="/changePassword" element={<ChangePassword />} />
                <Route path="/remindPassword/:token" element={<RemindPassword />} />
                <Route path="/forgotPassword" element={<ForgotPassword />} />
                <Route path="/resetPasswordEmailSent" element={<ResetPasswordEmailSent />} />
                <Route path="/products" element={<Products />} />
                <Route path="/product/:productId/:categoryId/:title" element={<ProductPage />} />
                <Route path="/products/amber" element={<ProductAmber />} />
                <Route path="/products/topaz" element={<ProductTopaz />} />
                <Route path="/products/amethyst" element={<ProductAmethyst />} />
                <Route path="/products/lapiz_lazuli" element={<ProductLapiz />} />
                <Route path="/products/quartz" element={<ProductQuartz />} />
                <Route path="/products/mountain_gem" element={<ProductMntGem />} />
                <Route path="/passResetExpired" element={<PassResetTokenExpire />} />
                <Route path="/resetExpired" element={<PassResetTokenNotFound />} />
                <Route path="*" element={<Error />} />
            </Routes>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById("root")
)
