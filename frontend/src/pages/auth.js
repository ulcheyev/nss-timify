import {useRef, useState, useEffect, Component} from "react";
import axios from 'axios';
import UserService from '../services/user.service'
import {User} from "../models/user";

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;


const Auth = () => {

    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');

    const register = (e) => {
        e.preventDefault();
    }

    const login = (e) => {
        e.preventDefault();
        UserService.login(new User(user, pwd))
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
        <div className="container">

            <div className="slider"></div>
            <div className="btn">
                <button className="login">Login</button>
                <button className="signup">Signup</button>
            </div>

            <div className="form-section">

                <form className="login-box" onSubmit={login}>
                    <input type="username"
                           className="username ele"
                           placeholder="username"
                           onChange={(e) => setUser(e.target.value)}/>
                        <input type="password"
                               className="password ele"
                               placeholder="password"
                               onChange={(e) => setPwd(e.target.value)}/>
                            <button className="clkbtn">Login</button>
                </form>

                <form className="signup-box" onSubmit={register}>
                    <input type="text"
                           className="name ele"
                           placeholder="Enter your name"/>
                        <input type="email"
                               className="email ele"
                               placeholder="youremail@email.com"/>
                            <input type="password"
                                   className="password ele"
                                   placeholder="password"/>
                                <input type="password"
                                       className="password ele"
                                       placeholder="Confirm password"/>
                                    <button className="clkbtn">Signup</button>
                </form>
            </div>
        </div>
)
}


export default Auth