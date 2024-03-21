import { useState, useTransition } from "react"
import { InputGroup } from "react-bootstrap"
import '../Styles/usersignup.css'
import axios from "axios"

const Usersignup=()=>{
    let [name,setname]=useState("")
    let [age,setage]=useState("")
    let [email , setEmail] =useState("")
    let [phone ,setphone]=useState("")
    let [password, setPassword]=useState("")

    let data={name,email,age,phone,password}

    let addMerchant =(e)=>{
        e.preventDefault();
        axios.post(`http://localhost:8080/user`,data)
        .then((res)=>{
            console.log(res);
            alert("Data added sucessfully")
        })
        .catch((err)=>{
            console.log(err.data);
            alert("Data Not Found")
        })
    }

    

    return( 
        <div className="merchantsignup">
            <form action=" " onSubmit={addMerchant}>
                <label htmlFor="">Name</label>
                <input value={name} onChange={(e)=>{setname(e.target.value)}} type="text" placeholder="Enter name " required/>

                <label htmlFor="">Age</label>
                <input value={age} onChange={(e)=>{setGst_number(e.target.value)}} type="text" placeholder="Enter age " required/>

                <label htmlFor="">Email</label>
                <input value={email} onChange={(e)=>{setEmail(e.target.value)}} type="text" placeholder="Enter email " required/>


                <label htmlFor="">phone No</label>
                <input value={phone} onChange={(e)=>{setphone(e.target.value)}} type="text" placeholder="Enter phone " required/>


                <label htmlFor="">password</label>
                <input value={password} onChange={(e)=>{setPassword(e.target.value)}} type="text" placeholder="Enter password " required/>


                <button className="btn btn-outline-info">Submit</button>



            </form>
        </div>
    )

}
export default Usersignup