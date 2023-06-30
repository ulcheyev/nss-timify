import {useRef, useState, useEffect, Component, useContext} from "react";
import UserService from '../services/user.service'
import NotificationService from '../services/notification.service'
import {LogUser} from "../models/logUser";
import {RegUser} from "../models/regUser";
import {useHistory} from "react-router-dom";
import {Context} from "../index";
import {TODO_ROUTE} from "../utils/consts";
import Cookies from 'js-cookie';

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const EMAIL_REGEX = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/
const Auth = () => {

    const {user} = useContext(Context)

    const history  = useHistory()
    const [username, setUsername] = useState('');
    const [usernameFocus, setUsernameFocus] = useState(false);

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
        const userTest = USER_REGEX.test(username)
        setValidUser(userTest)
    }, [username])

    useEffect(() => {
        const pwdTest = PWD_REGEX.test(pwd)
        setValidPwd(pwdTest)
        const match = pwd === confPwd
        setValidPwdMatch(match)
    }, [pwd, confPwd])

    useEffect(() => {
        const emailTest = EMAIL_REGEX.test(email)
        setValidEmail(emailTest)
    }, [email])

    useEffect(() => {
        setErrMsg('');
    }, [username, email, pwd, validPwdMatch])

    const register = (e) => {
        e.preventDefault();
        UserService.register(new RegUser(username, pwd, email)).then(res=>{
            try {
                console.log(res.data)
                setSuccess(true)
                user.setUser(user)
                user.setUsername(username)
                user.setIsAuth(true)
                user.setToken(res.data.token)
                history.push(TODO_ROUTE)
                NotificationService.sendEmail(res.data.id, "greetings", email)
            }catch (err){
                setErrMsg(res.response.data.message)
                setSuccess(false)
            }
        })
    }

    const login = (e) => {
        e.preventDefault();
        UserService.login(new LogUser(username, pwd)).then(res=>{
            try {
                let data = res.data.token;
                setSuccess(true)
                user.setUser(user)
                user.setUsername(username)
                user.setIsAuth(true)
                user.setToken(res.data.token)
                Cookies.set('jwtToken', data, { path: '/' });
                console.log(Cookies.get('jwtToken'))
                history.push(TODO_ROUTE)
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
            <div className="auth-container">
                <div className="slider"></div>
                <div className="slider-btn">
                    <button className="login">Login</button>
                    <button className="signup">Signup</button>
                </div>
                <p className="errorMsg">{errMsg}</p>

                <div className="form-section">

                    <form className="login-box" onSubmit={login}>

                        <input type="username"
                               className="username ele"
                               placeholder="Username"
                               onChange={(e) => setUsername(e.target.value)}
                               onFocus={() => setUsernameFocus(true)}
                               onBlur={() => setUsernameFocus(false)}
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
                                   className={usernameFocus && !validUser ? "name invalidEle" : "name ele"}
                                   placeholder="Username"
                                   onChange={(e) => setUsername(e.target.value)}
                                   onFocus={() => setUsernameFocus(true)}
                                   aria-invalid={validUser ? "false" : "true"}
                                   onBlur={() => setUsernameFocus(false)}
                                   required/>
                            <p className={usernameFocus && !validUser ? "instructions" : "hide"}>
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
        </>
    )
}
export default Auth