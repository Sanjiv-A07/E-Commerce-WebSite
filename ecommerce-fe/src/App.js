import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./Components/Landingpage";
import MerchantLogin from "./Components/MerchantLogin";
import UserLogin from "./Components/UserLogin";
import MerchantSignup from "./Components/MerchantSignup";
import Error from "./Components/Error";


import MerchantHomePage from "./Components/MerchantHomePage";
import Protect from "./Components/Protect";

// import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
       <BrowserRouter>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path ="/*" element={<Error/>}/>
          <Route path="/merchant" element={<MerchantLogin />} />
          <Route path="/User" element={<UserLogin />} />
          <Route path="/merchantsignup" element={<MerchantSignup/>}/>
          <Route path="/merchanthomepage/*" element={<Protect Child={MerchantHomePage}/>}/>
          

       
        </Routes>
      </BrowserRouter> 
    </div>
  );
}

export default App;