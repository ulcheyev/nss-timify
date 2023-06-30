import React, { useContext, useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { Context } from "../index";
import { observer } from "mobx-react-lite";
import { Row } from "reactstrap";

const API_URL_USERS = 'http://34.125.160.101:8080/api/v1/system/users'; //TODO change to 34.125.160.101
const API_URL_TASKS = 'http://34.125.160.101:8080/api/v1/core/tasks/all'; //TODO change to 34.125.160.101

const Profile = observer(() => {
    const { user } = useContext(Context);
    const [allTasks, setAllTasks] = useState([]);
    const [allUsers, setAllUsers] = useState([]);
    const [archivedTasks, setArchivedTasks] = useState([]);
    const [projectStats, setProjectStats] = useState([]);

    useEffect(() => {
        if (user.isAdmin) {
            fetch(API_URL_TASKS, {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": `Bearer ${Cookies.get('jwtToken')}`
                },
                withCredentials: true
            })
                .then(res => res.json())
                .then(respJson => {
                    setAllTasks(respJson);
                });
        } else {
            fetch(`http://34.125.160.101:8080/api/v1/core/tasks/archived?page=0&size=5`, { // TODO
                method: 'GET',
                headers: {
                    "Authorization": `Bearer ${Cookies.get('jwtToken')}`,
                    "Content-Type": "text/plain",
                },
            })
                .then(response => response.json())
                .then(respJson => {
                    setArchivedTasks(respJson);
                });
        }

        if (user.isAdmin) {
            fetch(API_URL_USERS, {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": `Bearer ${Cookies.get('jwtToken')}`
                },
                withCredentials: true
            })
                .then(res => res.json())
                .then(resJson => {
                    setAllUsers(resJson);
                });
        } else {
            fetch("http://34.125.160.101:8080/api/v1/core/projects/stats") //TODO
                .then(response => response.json())
                .then(respJson => {
                    setProjectStats(respJson);
                });
        }
    }, [user.isAdmin]);

    const sendPlay = (id) =>{
        const note  = document.getElementById(id);
        fetch(`http://34.125.160.101:8080/api/v1/core/tasks/start-task`, { //TODO
            method: "PUT",
            headers: { 'Content-Type': 'application/json' },
            body: `${id}`
        }).then((response) =>{
            if(response.status/100<3)
                alert("Task unarchived successfully");
        })
            .catch(e => alert(e))
    }

    return (
        <div>
            {user.isAdmin ? (
                <Row>
                    <div className="Statistic Users">
                        <h1 style={{ color: "#fea82f" }}>All Users</h1>
                        {allUsers.map(user => (
                            <div className="Task" key={user.id}>
                                <div className="UpperLine">
                                    <div className="TaskHead">{user.username}</div>
                                </div>
                            </div>
                        ))}
                    </div>
                    <div className="Statistic AllTasksContainer">
                        <h1 style={{ color: "#fea82f" }}>All Tasks of all users</h1>
                        {allTasks.map(task => (
                            <div className="Task" key={task.id}>
                                <div className="UpperLine">
                                    <div className="TaskHead">{task.name}</div>
                                    <div>Deadline: {task.deadline ? task.deadline : "No deadline"}</div>
                                </div>
                                <div className="LowerLine">
                                    <p className="Description">{task.description}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                </Row>
            ) : (
                <Row>
                    <div className="Statistic ProjectContainer">
                        <h1 style={{ color: "#fea82f" }}>Your projects</h1>
                        {projectStats.map(project => (
                            <div key={project.id}>
                                {project.name}, Days: {project.days}, Hours: {project.hours}, Minutes: {project.minutes}, Seconds: {project.seconds}
                            </div>
                        ))}
                    </div>
                    <div className="Statistic ArchivedTasksContainer">
                        <h1 style={{ color: "#fea82f" }}>Your archived tasks</h1>
                        {archivedTasks.map(task => (
                            <div className="Task" key={task.id}>
                                <div className="UpperLine">
                                    <div className="TaskHead">{task.name}</div>
                                    <div>Deadline: {task.deadline ? task.deadline : "No deadline"}</div>
                                </div>
                                <div className="LowerLine">
                                    <p className="Description">{task.description}</p>
                                </div>
                                <button onClick={() => sendPlay(task.id)}>Unarchive</button>
                            </div>
                        ))}
                    </div>
                </Row>
            )}
        </div>
    );
});

export default Profile;
