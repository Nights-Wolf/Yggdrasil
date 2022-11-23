import React from "react";

const Pagination = ({productsPerPage, totalProducts, paginate}) => {
    const url = window.location.href

    const pageNumbers = []

    for(let i = 1; i <= Math.ceil(totalProducts / productsPerPage); i++) {
        pageNumbers.push(i)
    }

    return(
        <div className="pagination">
            <ul>
                {pageNumbers.map(number => (
                    <li onClick={() => paginate(number)} key={number}>
                        {number}
                    </li>
                ))}
            </ul>
       </div>
    )
}

export default Pagination
