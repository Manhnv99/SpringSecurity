import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";


const Home=()=>{

    const nav=useNavigate();
    const [user,setUser]=useState({
        name:"",
        username:"",
        password:""
    });

    useEffect(() => {
        callAPI();
    }, []);

    const callAPI= async ()=>{
        const token = localStorage.getItem("token");
        try {
            const response= await axios.get("http://localhost:8080/api/v1/user/data",{
                headers:{
                    Authorization:`Bearer ${token}`
                }
            });
            if(response && response.status===200){
                setUser(response.data);
                console.log(response.data)
            }
        }catch (e) {
            if(e.response.status===403){
                nav("/login")
            }
        }
    }

    return(
        <div>
            <ul>
                <li>{user.name}</li>
                <li>{user.username}</li>
                <li>{user.password}</li>
            </ul>
        </div>
    )
}

export default Home