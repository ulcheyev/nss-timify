import {useRef, useState, useEffect, Component} from "react";
import UserService from '../services/user.service'
import {LogUser} from "../models/logUser";
import {RegUser} from "../models/regUser";
import Cookies from 'js-cookie';

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const EMAIL_REGEX = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/


const Auth = () => {

    const [user, setUser] = useState('');
    const [userFocus, setUserFocus] = useState(false);

    const [pwd, setPwd] = useState('');
    const [pwdFocus, setPwdFocus] = useState(false);

    const [email, setEmail] = useState('');
    const [emailFocus, setEmailFocus] = useState(false);

    const [confPwd, setConfPwd] = useState('');
    const [confPwdFocus, setConfPwdFocus] = useState(false);

    const [validUser, setValidUser] = useState(false);
    const [validEmail, setValidEmail] = useState(false);
    const [validPwd, setValidPwd] = useState(false);
    const [validPwdMatch, setValidPwdMatch] = useState(false);

    const [success, setSuccess] = useState(false);
    const [errMsg, setErrMsg] = useState('');

    useEffect(() => {
        const userTest = USER_REGEX.test(user)
        console.log('user ' + userTest)
        setValidUser(userTest)
    }, [user])

    useEffect(() => {
        const pwdTest = PWD_REGEX.test(pwd)
        console.log('pwd ' + pwdTest)
        setValidPwd(pwdTest)
        const match = pwd === confPwd
        setValidPwdMatch(match)
    }, [pwd, confPwd])

    useEffect(() => {
        const emailTest = EMAIL_REGEX.test(email)
        console.log('email ' + emailTest)
        setValidEmail(emailTest)
    }, [email])

    useEffect(() => {
        setErrMsg('');
    }, [user, email, pwd, validPwdMatch])

    const register = (e) => {
        e.preventDefault();
        UserService.register(new RegUser(user, pwd, email)).then(res=>{
            try {
                console.log(res.data.token)
                setSuccess(true)
            }catch (err){
                setErrMsg(res.response.data.message)
                setSuccess(false)
            }
        })
    }


    const login = (e) => {
        e.preventDefault();
        UserService.login(new LogUser(user, pwd)).then(res=>{
            try {
                let data = res.data.token;
                setSuccess(true)
                Cookies.set('jwtToken', data, { path: '/' });
                console.log(Cookies.get('jwtToken'))
            }catch (err){
                setErrMsg(res.response.data.message)
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

                    <p className="errorMsg">{errMsg}</p>

                    <div className="form-section">


                        <form className="login-box" onSubmit={login}>

                            <input type="username"
                                   className="username ele"
                                   placeholder="Username"
                                   onChange={(e) => setUser(e.target.value)}
                                   onFocus={() => setUserFocus(true)}
                                   onBlur={() => setUserFocus(false)}
                                   required/>

                            <input type="password"
                                   className="password ele"
                                   placeholder="Password"
                                   onChange={(e) => setPwd(e.target.value)}
                                   onFocus={() => setPwdFocus(true)}
                                   onBlur={() => setPwdFocus(false)}
                                   required/>
                            <button className="clkbtn">Login</button>
                        </form>

                        <form className="signup-box" onSubmit={register}>
                            <div className = "inp">

                            <input type="text"
                                   className={userFocus && !validUser ? "name invalidEle" : "name ele"}
                                   placeholder="Username"
                                   onChange={(e) => setUser(e.target.value)}
                                   onFocus={() => setUserFocus(true)}
                                   aria-invalid={validUser ? "false" : "true"}
                                   onBlur={() => setUserFocus(false)}
                                   required/>
                            <p className={userFocus && !validUser ? "instructions" : "hide"}>
                                4 to 24 characters.<br />
                                Must begin with a letter.
                                Letters, numbers, underscores, hyphens allowed.
                            </p>
                            </div>
                            <div className = "inp">
                                <input type="email"
                                       className={emailFocus && !validEmail ? "email invalidEle" : "email ele"}
                                       placeholder="youremail@email.com"
                                       onChange={(e) => setEmail(e.target.value)}
                                       onFocus={() => setEmailFocus(true)}
                                       onBlur={() => setEmailFocus(false)}
                                       aria-invalid={validEmail ? "false" : "true"}
                                       required/>
                                <p className={emailFocus && !validEmail ? "instructions" : "hide"}>
                                    Valid email example : email@domen.com
                                </p>
                            </div>
                            <div className = "inp">
                                <input type="password"
                                       className={pwdFocus && !validPwd ? "password invalidEle" : "password ele"}
                                       placeholder="Password"
                                       aria-invalid={validPwd ? "false" : "true"}
                                       onChange={(e) => setPwd(e.target.value)}
                                       onFocus={() => setPwdFocus(true)}
                                       onBlur={() => setPwdFocus(false)}
                                       required/>
                                <p className={pwdFocus && !validPwd ? "instructions" : "hide"}>
                                    8 to 24 characters.<br />
                                    Must include upper and lower letters, a number and a special character.
                                    Allowed special characters: ! @ # $ %
                                </p>
                            </div>
                            <div className = "inp">
                                <input type="password"
                                       className={confPwdFocus && !validPwdMatch ? "password invalidEle" : "password ele"}
                                       placeholder="Confirm password"
                                       aria-invalid={validPwdMatch ? "false" : "true"}
                                       onChange={(e)=>setConfPwd(e.target.value)}
                                       onFocus={() => setConfPwdFocus(true)}
                                       onBlur={() => setConfPwdFocus(false)}
                                       required/>
                                <p className={confPwdFocus && !validPwdMatch ? "instructions" : "hide"}>
                                   Must match the password above
                                </p>
                            </div>
                            <button className="clkbtn">Signup</button>
                        </form>
                    </div>
                </div>

            ))}
        </>
        )

}


export default Auth