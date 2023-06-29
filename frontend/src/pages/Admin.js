import React, {useEffect} from 'react';
import {Col, Container, Row} from "reactstrap";
import TaskList from "../components/TaskList";
import axios from "axios";
import Cookies from 'js-cookie';
import {observer} from "mobx-react-lite";
import TaskCard from "../components/TaskCard";

const API_URL_USERS = 'http://localhost:8080/api/v1/system/users'; //TODO change to 34.125.160.101
const API_URL_TASKS = 'http://localhost:8080/api/v1/core/tasks/all'; //TODO change to 34.125.160.101

const Admin = () => {

    // MOZHNO SDELAT PAGINACIYU
    let tasks =  fetch(API_URL_TASKS,
        {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${Cookies.get('jwtToken')}`
            },
            withCredentials: true
        }
    ).then(res => console.log(res.json()))

    let users = fetch(API_URL_USERS,
        {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${Cookies.get('jwtToken')}`
            },
            withCredentials: true
        }
    ).then(res => console.log(res.json()))


    return (
        <div>
            {}
        </div>
    );

}

export default Admin;