import logo from './logo.svg';
import './App.css';
import {useContext, useState} from "react";
import Auth from "./pages/Auth";
import {BrowserRouter} from "react-router-dom";
import NavBar from "./components/NavBar";
import {observer} from "mobx-react-lite";
import {Context} from "./index";
import AppRouter from "./components/AppRouter";
import Profile from "./pages/Profile";

const App = observer(() => {

    const {user} = useContext(Context)
    return (
        <BrowserRouter>
            <NavBar/>
            <AppRouter/>
        </BrowserRouter>
    );
});
export default App;
