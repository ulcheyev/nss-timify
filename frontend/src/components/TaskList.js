import React, {Component} from "react";
import "./TaskList.css"
class TaskList extends Component
{
    state = {
        tasks:[],
        categories:new Map()
        }


    componentDidMount() {
        fetch("http://34.125.160.101:8080/api/v1/core/categories")
            .then(response => response.json())
            .then(respJson => respJson.map(category => this.state.categories.set(category.id, category.name)))
        fetch('http://34.125.160.101:8080/api/v1/core/tasks/all')
            .then(response => response.json())
            .then(respJson => this.setState({tasks:respJson}))
    }


    sendPlay(id)
    {
        console.log(id)
        const note  = document.getElementById(id);
        const stopButton = document.getElementById(id).children[1].children[1].children[0];
        const startButton = document.getElementById(id).children[1].children[1].children[1];
        fetch(`http://localhost:8080/api/v1/core/tasks/start-task`, {
            method: "PUT",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(
                `${id}`
            )
        }).then((response) =>{
            if(response.status/100<3)
            alert("Task started successfully");
            stopButton.classList.remove("Hidden")
            startButton.classList.add("Hidden")
        })
            .catch(e => alert(e))
    }

    sendStop(id)
    {
        console.log(id)
        const note  = document.getElementById(id);
        const stopButton = document.getElementById(id).children[1].children[1].children[0];
        const startButton = document.getElementById(id).children[1].children[1].children[1];
        fetch(`http://localhost:8080/api/v1/core/tasks/stop-task`, {
            method: "PUT",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(
                `${id}`
            )
        }).then(response => {
            if(response.status/100<3) {
                alert("Task stopped successfully");
                startButton.classList.remove("Hidden")
                stopButton.classList.add("Hidden")
            }
            else
            {
                console.log(response)
            }
        })
            .catch(e => alert(e))
    }

    render()
    {
        const l =
            <div className={"TaskContainer"}> {
                this.state.tasks.map(task =>


                <div className={"Task"} key = {task.id} id={task.id}>

                    {task.status}
                    <div className={"UpperLine"}>
                        <div className={"TaskHead"}>
                            {task.name}
                        </div>
                        <div>Deadline: </div>
                    </div>
                    <div className={"LowerLine"}>
                    <p className={"Descritpion"}>
                        {task.description}
                    </p>
                        <div className = {"ButtonContainer"}>

                            <button className={`StopButton${(task.status != "ACTIVE" ? " Hidden" : "")}`} onClick={() => this.sendStop(task.id)}>Stop</button>
                            <button className={`StartButton${(task.status == "ACTIVE" ? " Hidden" : "")}`} onClick={() => this.sendPlay(task.id)}>Play</button>

                        </div>
                    <div className={"Categories"}>
                    Categories:
                    {task.categoryDtoList.map(element =>
                        <div>
                            {this.state.categories.has(element.id)?this.state.categories.get(element.id):"Unknown category"}
                        </div>)}
                </div>
                    </div>
                </div>)}
            </div>
        return l
    }

}
export default TaskList;