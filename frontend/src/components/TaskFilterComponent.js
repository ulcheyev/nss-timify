import TaskList from "./TaskList";
import React from "react";

class TaskFilterComponent extends TaskList
{
    state = {
        tasks: [],
        categories:new Map(),
        projects: new Map()
    }


    /*
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
                </div>
    */
    taskToHTML(task)
    {
        return(`
        <div class="Task" key=${task.id} id=${task.id}>

            ${task.status}
            <div class="UpperLine">
                <div class="TaskHead">
                    ${task.name}
                </div>
                <div>Deadline: </div>
            </div>
            <div class="LowerLine">
                <p class="Descritpion">
                    ${task.description}
                </p>
            </div>
            <div class=Categories">
               Categories:
                    ${task.categoryDtoList.map(element =>
                        <div>
                            {this.state.categories.has(element.id)?this.state.categories.get(element.id):"Unknown category"}
                        </div>)}
            </div>
        </div>
              
`)
    }

    replaceContent()
    {
        const projectId = document.getElementById('projectSelect').value
        const container = document.getElementsByClassName("TaskContainer")[0]
        container.innerHTML = ""
        fetch(`http://localhost:8080/api/v1/core/projects/${projectId}/tasks`)
            .then(response => response.json())
            .then(respJson => respJson.map(task =>
            container.innerHTML+= this.taskToHTML(task)))
    }

    componentDidMount() {
        const select = document.getElementById('projectSelect')
        fetch("http://localhost:8080/api/v1/core/categories")
            .then(response => response.json())
            .then(respJson => respJson.map(category => this.state.categories.set(category.id, category.name)))
        fetch(`http://localhost:8080/api/v1/core/projects`)
            .then(response => response.json())
            .then(respJson => respJson.map(project => select.innerHTML += (`<option value=${project.id}>${project.name}</option>`)))
    }

    render()
    {
        return(<div className={"bigContainer"}>
            <select id = {'projectSelect'}>

            </select>
            <button onClick={() => this.replaceContent()}>Apply filter</button>
            <div className={'TaskContainer'}>

            </div>
        </div>)
    }
}
export default TaskFilterComponent