import {Component} from "react";
import {Button} from "reactstrap";

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
            <div className={'new-todo-form'}>
                <div>
                    <input name={"name"} placeholder={'Name'} id = {'name'}/>
                    <input name={"description"} placeholder={'Description'} id = {'description'}/>
                </div>
                <div>
                    <label htmlFor={'target_time'}>Deadline</label>
                    <input type={"datetime-local"} id = {'target_time'}/>
                </div>
                <div className={'new-todo-selectors'}>
                    <span>Choose project:</span>
                    <select id = "projectSelect"></select>

                    <span>Choose main category:</span>
                    <select id = "categoriesSelect"></select>
                </div>
                <div>
                    <Button className={'btn-primary'} type = {"submit"} onClick={this.sendData}> Submit</Button>
                </div>
            </div>
        </form>)
    }
}

export default AddToProjectForm