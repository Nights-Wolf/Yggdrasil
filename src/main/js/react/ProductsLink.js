import { useLocation } from 'react-router-dom';

function ProductLink(children, to) {
    const location = useLocation()
    const match = location.pathname === to
    return (
        <Link to={to}>{children}</Link>
    )
}

export default ProductLink
