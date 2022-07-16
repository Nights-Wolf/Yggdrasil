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
                <Route path="/product/:categoryId/:productId" element={<ProductPage />} />
                <Route path="*" element={<Error />} />
            </Routes>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById("root")
)
