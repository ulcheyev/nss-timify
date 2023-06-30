import React, {useContext} from 'react';
import {observer} from "mobx-react-lite";
import {ListContext} from "./TaskList.js";


const TaskCard = ({task}) => {

    const setRender = useContext(ListContext)

    const categories = new Map()

    fetch("http://localhost:8080/api/v1/core/categories") // TODO change to 34.125.160.101
        .then(response => response.json())
        .then(respJson => respJson.map(category => categories.set(category.categoryId, category.name)))

    const sendPlay = (id) =>{
        console.log(id)
        const note  = document.getElementById(id);
        const stopButton = document.getElementById(id).children[1].children[1].children[0];
        const startButton = document.getElementById(id).children[1].children[1].children[1];
        fetch(`http://localhost:8080/api/v1/core/tasks/start-task`, {
            method: "PUT",
            headers: { 'Content-Type': 'application/json' },
            body: `${id}`
        }).then((response) =>{
            if(response.status/100<3)
                alert("Task started successfully");
            stopButton.classList.remove("Hidden")
            startButton.classList.add("Hidden")
        })
            .catch(e => alert(e))
    }

    const sendStop = (id) =>
    {
        console.log(id)
        const note  = document.getElementById(id);
        const stopButton = document.getElementById(id).children[1].children[1].children[0];
        const startButton = document.getElementById(id).children[1].children[1].children[1];
        fetch(`http://localhost:8080/api/v1/core/tasks/stop-task`, {
            method: "PUT",
            headers: { 'Content-Type': 'application/json' },
            body: `${id}`
        }).then(response => {
            if(response.status/100<3) {
                alert("Task stopped successfully");
                startButton.classList.remove("Hidden")
                stopButton.classList.add("Hidden")
            }
            else
            {
                console.log(response.body)
            }
        })
            .catch(e => alert(e))
    }

    const sendArchive = (id) =>
    {
        console.log(id)
        const note  = document.getElementById(id);
        fetch(`http://localhost:8080/api/v1/core/tasks/archive-task`, {
            method: "PUT",
            headers: { 'Content-Type': 'application/json' },
            body: `${id}`
        }).then(response => {
            if(response.status/100<3) {
                alert("Task archived successfully");
                note.parentNode.removeChild(note);
                setRender(id)
            }
            else
            {
                console.log(response.body)
            }
        })
            .catch(e => alert(e))
    }

    return(
                <div className={"Task"} id={task.id}>
                    <div className={"UpperLine"}>
                        <div className={"TaskHead"}>
                            {task.name}
                        </div>
                        <div>Deadline: {task.deadline ? task.deadline : "No deadline"}</div>
                    </div>
                    <div className={"LowerLine"}>
                        <p className={"Descritpion"}>
                            {task.description}
                        </p>
                        <div className = {"ButtonContainer"}>

                            <button className={`StopButton${(task.status !== "ACTIVE" ? " Hidden" : "")}`} onClick={() => sendStop(task.id)}>Stop</button>
                            <button className={`StartButton${(task.status === "ACTIVE" ? " Hidden" : "")}`} onClick={() => sendPlay(task.id)}>Play</button>
                            <button className={`ArchiveButton`} onClick={() => sendArchive(task.id)}>Archive</button>

                        </div>
                        <div className={"Categories"}>
                            Categories:
                            {categories ? task.categoryDtoList.map(element =>
                                <div>
                                    {categories.has(element.id)?this.state.categories.get(element.id):"Unknown category"}
                                </div>)
                                :
                                <span>
                                   No categories yet
                                </span>
                            }
                        </div>
                    </div>
                </div>
    );
};

export default TaskCard;