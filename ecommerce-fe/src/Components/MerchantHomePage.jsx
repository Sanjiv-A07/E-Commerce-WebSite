import { Route, Routes } from "react-router-dom";
import MerchantNavbar from "./MerchantNavbar";
import ViewProduct from "./ViewProduct";
import UpdateMerchant from "./UpdateMerchant";
import AddProduct from "./AddProduct";

const MerchantHomePage = () => {
    return (
        <div className="mhp">
            <MerchantNavbar/>
           <Routes>
            <Route path="/viewproduct" element={<ViewProduct/>}/>
            <Route path="/updatemerchant" element={<UpdateMerchant/>}/>
            <Route path="/addProduct"  element={<AddProduct/>}/>
           </Routes>
        </div>
      );
}
 
export default MerchantHomePage;