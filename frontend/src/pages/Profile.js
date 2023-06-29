import React from 'react';
import Cookies from 'js-cookie';
const Profile = () => {
    const queryParams = new URLSearchParams(window.location.search)
    let userComponent = "";
    const token = Cookies.get('jwtToken');
    console.log(token)
    fetch("http://localhost:8080/api/v1/core/projects/stats",
        )
        .then(response => response.json())
        .then(respJson =>
        {
            const container = document.getElementsByClassName("ProjectContainer")[0]
            respJson.map(
                    project =>
                    {
                        let allSecs = project.seconds;
                        const seconds = allSecs%60;
                        allSecs = Math.floor(allSecs/60);
                        const minutes = allSecs%60;
                        allSecs = Math.floor(allSecs/60);
                        const hours = allSecs%60;
                        allSecs = Math.floor(allSecs/24);;
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
    fetch(`http://localhost:8080/api/v1/core/tasks/archived`,{
        method: 'GET',
        headers: {
            "Authorization": 'Bearer ' + token,
            "Content-Type": "text/plain",
        },

    })
        .then(response => response.json())
        .then(respJson =>
            {
                const container = document.getElementsByClassName("ArchivedTasksContainer")[0]
                respJson.map(
                    task =>
                    {
                        let allSecs = task.timeSpent;
                        const seconds = allSecs%60;
                        allSecs = Math.floor(allSecs/60);
                        const minutes = allSecs%60;
                        allSecs = Math.floor(allSecs/60);
                        const hours = allSecs%60;
                        allSecs = Math.floor(allSecs/24);;
                        const days = allSecs;
                        container.innerHTML += (`<div><b>${task.name}</b>,
                            <p>${task.description}</p>
                            Deadline: ${task.deadline}
                            <b>Spent:</b>
                            Days: ${days},
                            Hours: ${hours},
                            Minutes: ${minutes},
                            Seconds: ${seconds}
                        </div>`)
                        console.log(task)
                    }
                )
            }
        )

    return (
        <div>
        <div className={"ProjectContainer"}>
            add Here
            {userComponent}
        </div>
        <div className={"ArchivedTasksContainer"}></div>
        </div>
    );
};

export default Profile;