import axios from 'axios'
import React, { useState } from 'react'
import "../Styles/AddProduct.css"




 const AddProduct = () => {

    let[name,setname]=useState("")
    let[brand,setbrand]=useState("")
    let[description,setdescription]=useState("")
    let[cost,setcost]=useState("")
    let[category,setcategory]=useState("")
    let[image_url,setimage_url]=useState("")

    let data ={name , brand,category,description,cost,image_url}


  
    let x = JSON.parse(localStorage.getItem("merchant"))
    let addData = (e)=>{
        e.preventDefault();
       axios.post(`http://localhost:8080/products/${x.id}`,data)
        .then((res)=>{
            console.log(res);
            alert("product Added Sucessfully")
        })
        .catch((err)=>{
            console.log(err);
            alert("something went worng ");
        })
    }


  return (
    <>
     <div className="AddProduct">
        <form onSubmit={addData} action="">
            <label htmlFor="">Name:</label>
            <input value={name} onChange={(e)=>{setname(e.target.value)}} type="text" required placeholder='Enter  name '/>
            <label htmlFor=''>Brand:</label>
            <input value={brand} onChange={(e)=>{setbrand (e.target.value)}} type='text' required placeholder='Enter  brand'/>
            <label htmlFor='' >Category:</label>
            <input value={category} onChange={(e)=>{setcategory(e.target.value)}} type='text' required placeholder='Enter  category'/>
            <label htmlFor=''>Description:</label>
            <input value={description} onChange={(e)=>{setdescription(e.target.value)}} type="text" required placeholder='Enter description'/>
            <label htmlFor=''>cost:</label>
            <input value={cost} onChange={(e)=>{setcost(e.target.value)}} type="number" required placeholder='Enter cost'/>
            <label htmlFor=''>Image_url:</label>
            <input value={image_url} onChange={(e)=>{setimage_url(e.target.value)}} type="text" required placeholder='Enter Image Url'/>
            
            <button className='btn btn-sucess'> Submit</button>

        </form>




     </div>

    </>
   
  )
}
export default AddProduct;
