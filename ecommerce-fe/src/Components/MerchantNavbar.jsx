
import React from 'react'
import { Link } from 'react-router-dom'
import '../Styles/MerchantNavbar.css'
import Dropdown from 'react-bootstrap/Dropdown';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import AddProduct from './AddProduct';
function MerchantNavabr() {
  return (
    <nav id='navbar'>
        <div className="logo">
            <h1><span>Shoppers</span>Cart</h1>
        </div>
        
        <div className="option">
            <Link to="/merchanthomepage/viewproduct">View Products</Link>
            <Link to ="/merchanthomepage/addProduct">Add Product</Link>
        </div>


        <div className="account">
        <Dropdown>
      <Dropdown.Toggle variant="outline-dark" id="dropdown-basic">
      <AccountCircleIcon/> Account
      </Dropdown.Toggle>

      <Dropdown.Menu>
        <Dropdown.Item href="/merchanthomepage/updatemerchant">Edit Account</Dropdown.Item>
        <Dropdown.Item href="/">Logout</Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>
        </div>
    </nav>
  )
}
export default MerchantNavabr
