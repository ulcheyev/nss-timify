import {Component} from "react";

class AddToProjectForm extends Component
{


    componentDidMount() {
        const select = document.getElementById('projectSelect')
        const categories = document.getElementById("categoriesSelect")
        fetch("http://localhost:8080/api/v1/core/projects")
            .then(response => response.json())
            .then(respJson => respJson.map(project =>
            {
                this.state.projects.set(project.id, project.name)
                select.innerHTML += (`<option value=${project.id}>${project.name}</option>`)
            }))

        fetch("http://localhost:8080/api/v1/core/categories")
            .then(response => response.json())
            .then(respJson => respJson.map(category =>
            {
                //this.state.categories.set(project.id, project.name)
                categories.innerHTML += (`<option value=${category.categoryId}>${category.name}</option>`)
            }))
    }

    state = {
        projects:new Map()
    }
    sendData(e)
    {
        e.preventDefault();
        const name = document.getElementById('name').value;
        const description = document.getElementById('description').value;
        const datetime = document.getElementById('target_time').value;
        const option = document.getElementById("projectSelect").value;
        const category = document.getElementById("categoriesSelect").value;
        /*
        * {
  "taskId": 0,
  "description": "string",
  "name": "string",
  "startTime": "2023-06-28T15:07:07.229Z",
  "subtasks": [
    "string"
  ],
  "project": "string",
  "owner": "string",
  "status": "ACTIVE"
}*/
        console.log(`
            {
            "description":"${description}",
            "name":"${name}",
            "deadline":"${datetime}",
            "projectId":"${option}",
            "categoryDtoList":[{"id":${category}}],
            "user":"test"
            }
            `)
        fetch(`http://localhost:8080/api/v1/core/tasks`, { //TODO change url
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: `
            {
            "description":"${description}",
            "name":"${name}",
            "deadline":"${datetime}",
            "projectId":"${option}",
            "categoryDtoList":[{"id":${category}}],
            "user":"test"
            }
            `
        }).then(response => {
            if(response.status/100<3) {
                alert("Task added successfully");

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

        return(
        <form className={"AddTaskForm"}>
            <input name={"name"} id = {'name'}/>
            <input name={"description"} id = {'description'}/>
            <input type={"datetime-local"} id = {'target_time'}/>
            <b>Project</b>
            <select id = "projectSelect">
            </select>
            <b>Main category</b>
            <select id = "categoriesSelect">
            </select>
            <input type = {"submit"} onClick={this.sendData}/>
        </form>)
    }


}

export default AddToProjectForm