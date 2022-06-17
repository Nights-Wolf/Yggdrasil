import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { createBrowserHistory } from "history";
import App from "./react/App";
import About from "./react/About";
import Terms from "./react/Terms";
import Contact from "./react/Contact";
import Rodo from "./react/RODO";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter history={createBrowserHistory}>
            <Routes>           
                <Route path="/" element={<App />} />
                <Route path="/about" element={<About />} />
                <Route path="/terms" element={<Terms />} />
                <Route path="/contact" element={<Contact />} />
                <Route path="/rodo" element={<Rodo />} />
            </Routes>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById("root")
)
