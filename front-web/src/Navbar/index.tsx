import './styles.css';
import { ReactComponent as Logo } from './logo.svg';
import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <nav className="main-navbar">

            <Link to="/home" className="logo-text">
                <Logo />
            </Link>
        </nav>
    )
}
export default Navbar;