import {useNavigate} from "react-router-dom";
import {useState} from "react";
import axios from "axios";

const Register=()=>{

    const nav=useNavigate();

    const [user,setUser]=useState({
        name:"",
        username:"",
        password:"",
        roleId:""
    })

    const onChangeName=(e)=>{
        const newUser={...user};
        newUser.name=e.target.value;
        setUser(newUser)
    }

    const onChangeUserName=(e)=>{
        const newUser={...user};
        newUser.username=e.target.value;
        setUser(newUser)
    }
    const onChangePassword=(e)=>{
        const newUser={...user};
        newUser.password=e.target.value;
        setUser(newUser)
    }

    const onChangeRole=(e)=>{
        const newUser={...user};
        newUser.roleId=e.target.value;
        setUser(newUser)
    }
    const handleSignUp= async ()=>{
        try {
            const res= await axios.post("http://localhost:8080/api/v1/auth/sign-up",user);
            if(res && res.status===201){
                nav("/login");
            }
        }catch (e){
            console.log(e)
        }
    }


    return(
        <section className="vh-100" style={{backgroundColor:"#eee"}}>
            <div className="container h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-lg-12 col-xl-11">
                        <div className="card text-black" style={{borderRadius:"25px"}}>
                            <div className="card-body p-md-5">
                                <div className="row justify-content-center">
                                    <div className="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                        <p className="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                                        <form className="mx-1 mx-md-4">
                                            <div className="d-flex flex-row align-items-center mb-4">
                                                <i className="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div className="form-outline flex-fill mb-0">
                                                    <input value={user.name} onChange={onChangeName} type="text" className="form-control"/>
                                                    <label className="form-label">Your
                                                        Name</label>
                                                </div>
                                            </div>

                                            <div className="d-flex flex-row align-items-center mb-4">
                                                <i className="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                <div className="form-outline flex-fill mb-0">
                                                    <input onChange={onChangeUserName} value={user.username} type="text" className="form-control"/>
                                                    <label className="form-label">UserName</label>
                                                </div>
                                            </div>

                                            <div className="d-flex flex-row align-items-center mb-4">
                                                <i className="fas fa-lock fa-lg me-3 fa-fw"></i>
                                                <div className="form-outline flex-fill mb-0">
                                                    <input onChange={onChangePassword} value={user.password} type="password"
                                                           className="form-control"/>
                                                    <label className="form-label">Password</label>
                                                </div>
                                            </div>

                                            <div className="d-flex flex-row align-items-center mb-4">
                                                <i className="fas fa-key fa-lg me-3 fa-fw"></i>
                                                <div className="form-outline flex-fill mb-0">
                                                    <input value="1" onChange={onChangeRole} type="radio" name="gender"/>
                                                    <label className="form-label">Admin</label>
                                                    <input value="2" onChange={onChangeRole} type="radio" name="gender"/>
                                                    <label className="form-label">User</label>
                                                </div>
                                            </div>

                                            <div className="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                                <button onClick={handleSignUp} type="button" className="btn btn-primary btn-lg">Register
                                                </button>
                                                <button onClick={()=>{nav("/login")}} type="button" className="btn btn-primary btn-light btn-outline-secondary" style={{marginLeft:"10px"}}>Go back
                                                </button>
                                            </div>
                                        </form>

                                    </div>
                                    <div
                                        className="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                        <img
                                            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                            className="img-fluid" alt="Sample image"/>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default Register