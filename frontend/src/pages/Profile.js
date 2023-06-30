import React, {useContext} from 'react';
import Cookies from 'js-cookie';
import {Context} from "../index";
import {observer} from "mobx-react-lite";
import {Row} from "reactstrap";

const API_URL_USERS = 'http://localhost:8080/api/v1/system/users'; //TODO change to 34.125.160.101
const API_URL_TASKS = 'http://localhost:8080/api/v1/core/tasks/all'; //TODO change to 34.125.160.101
const Profile = (() => {

    const {user} = useContext(Context)



    if (user.isAdmin) {
        fetch(API_URL_TASKS, {
                    headers: {
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${Cookies.get('jwtToken')}`
                    },
                    withCredentials: true
                }
            ).then(res => res.json())
                .then(respJson => {

                    const container = document.getElementsByClassName("AllTasksContainer")[0]
                    respJson.map(task => {
                        container.innerHTML += (`
                        <div className={"Task"} id={task.id}>
                            <div className={"UpperLine"}>
                                <div className={"TaskHead"}>
                                    ${task.name}
                                </div>
                                <div>Deadline: ${task.deadline ? task.deadline : "No deadline"}</div>
                            </div>
                            <div className={"LowerLine"}>
                                <p className={"Descritpion"}>
                                    ${task.description}
                                </p>
                            </div>
                        </div>`)
                    })
                }
            )
    }

    if (user.isAdmin) {
        fetch(API_URL_USERS, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${Cookies.get('jwtToken')}`
            },
            withCredentials: true
            }
        ).then(res => res.json())
            .then(resJson => {
                const container = document.getElementsByClassName("Users")[0]
                resJson.map(user => {
                    container.innerHTML += (`
                        <div className={"Task"} id={user.id}>
                        <div className={"UpperLine"}>
                            <div className={"TaskHead"}>
                                ${user.username}
                            </div>
                        </div>
                    </div>
                    `)
                })
            }
        )
    }


    const queryParams = new URLSearchParams(window.location.search)
    let userComponent = "";
    console.log(Cookies.get('jwtToken'))

    if (!user.isAdmin) {
        fetch("http://localhost:8080/api/v1/core/projects/stats",
        )
            .then(response => response.json())
            .then(respJson => {
                    const container = document.getElementsByClassName("ProjectContainer")[0]
                    respJson.map(
                        project => {
                            let allSecs = project.seconds;
                            const seconds = allSecs % 60;
                            allSecs = Math.floor(allSecs / 60);
                            const minutes = allSecs % 60;
                            allSecs = Math.floor(allSecs / 60);
                            const hours = allSecs % 60;
                            allSecs = Math.floor(allSecs / 24);
                            const days = allSecs;
                            container.innerHTML += (`<div>${project.name},
                            Days: ${days},
                            Hours: ${hours},
                            Minutes: ${minutes},
                            Seconds: ${seconds}
                        </div>`)
                            console.log(project)
                        }
                    )
            }
        )
    }
    if (!user.isAdmin) {
        fetch(`http://localhost:8080/api/v1/core/tasks/archived?page=0&size=5`,{
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${Cookies.get('jwtToken')}`,
                "Content-Type": "text/plain",
            },
        })
            .then(response => response.json())
            .then(respJson => {
                const container = document.getElementsByClassName("ArchivedTasksContainer")[0];
                respJson.forEach(task => {

                    const sendPlay = (id) =>{
                        console.log(id)
                        const note  = document.getElementById(id);
                        fetch(`http://localhost:8080/api/v1/core/tasks/start-task`, {
                            method: "PUT",
                            headers: { 'Content-Type': 'application/json' },
                            body: `${id}`
                        }).then((response) =>{
                            if(response.status/100<3)
                                alert("Task unarchived successfully");
                        })
                            .catch(e => alert(e))
                    }

                    const { id, name, deadline, description } = task;
                    let allSecs = task.timeSpent;
                    const seconds = allSecs % 60;
                    allSecs = Math.floor(allSecs / 60);
                    const minutes = allSecs % 60;
                    allSecs = Math.floor(allSecs / 60);
                    const hours = allSecs % 60;
                    allSecs = Math.floor(allSecs / 24);
                    const days = allSecs;
                    container.innerHTML += `
                      <div class="Task" id="${id}">
                        <div class="UpperLine">
                          <div class="TaskHead">
                            ${name}
                          </div>
                          <div>Deadline: ${deadline ? deadline : "No deadline"}</div>
                        </div>
                        <div class="LowerLine">
                          <p class="Descritpion">
                            ${description}
                          </p>
                        </div>
                        <button onclick={() => sendPlay(id)}>Unarchive</button>
                      </div>
                    `;
                    console.log(task);
                });
            });
    }

    return (
        <div>
            {user.isAdmin ?
                (<Row>
                    <div className={"Statistic Users"}>
                        <h1 style={{color: "#fea82f"}}> All Users</h1>
                    </div>
                    <div className={"Statistic AllTasksContainer"}>
                        <h1 style={{color: "#fea82f"}}> All Tasks of all users</h1>
                    </div>
                </Row>
                )
                :
                (<Row>
                    <div className={"Statistic ProjectContainer"}>
                        <h1 style={{color: "#fea82f"}}>Your projects</h1>
                    </div>
                    <div className={"Statistic ArchivedTasksContainer"}>
                        <h1 style={{color: "#fea82f"}}> Your archived tasks</h1>
                    </div>
                </Row>)


            }
        </div>
    );
});

export default Profile;