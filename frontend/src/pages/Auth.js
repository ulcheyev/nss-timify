import {useRef, useState, useEffect, Component, useContext} from "react";
import axios from 'axios';
import UserService from '../services/user.service'
import {LogUser} from "../models/logUser";
import {RegUser} from "../models/regUser";
import {useHistory} from "react-router-dom";
import {Context} from "../index";
import {TODO_ROUTE} from "../utils/consts";

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;


const Auth = () => {
    const history  = useHistory()
    const {user} = useContext(Context)
    const [username, setUsername] = useState('');
    const [pwd, setPwd] = useState('');
    const [email, setEmail] = useState('');

    const register = (e) => {
        e.preventDefault();
        UserService.register(new RegUser(username, pwd, email)).then(res=>{
            try {
                console.log(res.data.token)
                user.setUser(user)
                user.setIsAuth(true)
                user.setToken(res.data.token)
                history.push(TODO_ROUTE)
            }catch (err){
                console.log(res.response.data.message)
            }
        })
    }

    const login = (e) => {
        e.preventDefault();

        // validation input

        UserService.login(new LogUser(username, pwd)).then(res=>{
            try {
                console.log(res.data.token)
                user.setUser(user)
                user.setIsAuth(true)
                user.setToken(res.data.token)
                history.push(TODO_ROUTE)
            }catch (err){
                console.log(res.response.data.message)
            }
        })
    }

    useEffect(() => {
        let signup = document.querySelector(".signup");
        let login = document.querySelector(".login");
        let slider = document.querySelector(".slider");
        let formSection = document.querySelector(".form-section");

        signup.addEventListener("click", () => {
            slider.classList.add("moveslider");
            formSection.classList.add("form-section-move");
        });

        login.addEventListener("click", () => {
            slider.classList.remove("moveslider");
            formSection.classList.remove("form-section-move");
        });
    }, [])


    return (
            <div className="auth-container">
                <div className="slider"></div>
                <div className="slider-btn">
                    <button className="login">Login</button>
                    <button className="signup">Signup</button>
                </div>

                <div className="form-section">
                    <form className="login-box" onSubmit={login}>
                        <input type="username"
                               className="username ele"
                               placeholder="Username"
                               onChange={(e) => setUsername(e.target.value)}
                               required/>
                        <p></p>
                        <input type="password"
                               className="password ele"
                               placeholder="Password"
                               onChange={(e) => setPwd(e.target.value)}
                               required/>
                        <p></p>
                        <button className="clkbtn">Login</button>
                    </form>

                    <form className="signup-box" onSubmit={register}>
                        <input type="text"
                               className="name ele"
                               placeholder="Username"
                               onChange={(e) => setUsername(e.target.value)}
                               required/>
                        <p></p>
                        <input type="email"
                               className="email ele"
                               placeholder="youremail@email.com"
                               onChange={(e) => setEmail(e.target.value)}
                               required/>
                        <p></p>
                        <input type="password"
                               className="password ele"
                               placeholder="Password"
                               onChange={(e) => setPwd(e.target.value)}
                               required/>
                        <p></p>
                        <input type="password"
                               className="password ele"
                               placeholder="Confirm password"
                               required/>
                        <p></p>
                        <button className="clkbtn">Signup</button>
                    </form>
                </div>
            </div>
        )

}


export default Auth