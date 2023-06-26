import {useRef, useState, useEffect, Component} from "react";
import axios from 'axios';
import UserService from '../services/user.service'
import {LogUser} from "../models/logUser";
import {RegUser} from "../models/regUser";

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;


const Auth = () => {

    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [email, setEmail] = useState('');
    const [success, setSuccess] = useState(false);

    const register = (e) => {
        e.preventDefault();
        UserService.register(new RegUser(user, pwd, email)).then(res=>{
            try {
                console.log(res.data.token)
                setSuccess(true)
            }catch (err){
                console.log(res.response.data.message)
                setSuccess(false)
            }
        })
    }

    const login = (e) => {
        e.preventDefault();
        UserService.login(new LogUser(user, pwd)).then(res=>{
            try {
                console.log(res.data.token)
                setSuccess(true)
            }catch (err){
                console.log(res.response.data.message)
                setSuccess(false)
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
        <>
            {(success ? (
                <section>
                    <h1>Стоматолог: - Не бойся Серёга, все бывает в первый раз!
                        Пациент: - Я не Серёга.
                        Стоматолог: - Я знаю. Серёга это я... </h1>
                </section>):(
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
                                   placeholder="Username"
                                   onChange={(e) => setUser(e.target.value)}/>
                            <input type="password"
                                   className="password ele"
                                   placeholder="Password"
                                   onChange={(e) => setPwd(e.target.value)}/>
                            <button className="clkbtn">Login</button>
                        </form>

                        <form className="signup-box" onSubmit={register}>
                            <input type="text"
                                   className="name ele"
                                   placeholder="Username"
                                   onChange={(e) => setUser(e.target.value)}/>
                            <input type="email"
                                   className="email ele"
                                   placeholder="youremail@email.com"
                                   onChange={(e) => setEmail(e.target.value)}/>
                            <input type="password"
                                   className="password ele"
                                   placeholder="Password"
                                   onChange={(e) => setPwd(e.target.value)}/>
                            <input type="password"
                                   className="password ele"
                                   placeholder="Confirm password"/>
                            <button className="clkbtn">Signup</button>
                        </form>
                    </div>
                </div>

            ))}
        </>
        )

}


export default Auth