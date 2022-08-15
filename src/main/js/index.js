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
import Error from "./react/ErrorPage";

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
                <Route path="/products" element={<Products />} />
                <Route path="/product/:productId/:categoryId/:title" element={<ProductPage />} />
                <Route path="/products/amber" element={<ProductAmber />} />
                <Route path="/products/topaz" element={<ProductTopaz />} />
                <Route path="/products/amethyst" element={<ProductAmethyst />} />
                <Route path="/products/lapiz_lazuli" element={<ProductLapiz />} />
                <Route path="/products/quartz" element={<ProductQuartz />} />
                <Route path="/products/mountain_gem" element={<ProductMntGem />} />
                <Route path="*" element={<Error />} />
            </Routes>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById("root")
)
