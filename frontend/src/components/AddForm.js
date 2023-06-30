import {useContext, useEffect, useState} from "react";
import {Button} from "reactstrap";
import {observer} from "mobx-react-lite";
import {Context} from "../index";
import {TodoContext} from "../pages/ToDo";

const AddToProjectForm = observer(() =>{

    const {user} = useContext(Context)
    const setUpdate = useContext(TodoContext)
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [target, setTarget] = useState('');
    const [projectSelect, setProjectSelect] = useState('');
    const [categoriesSelect, setCategoriesSelect] = useState('');

    useEffect (() => {
        const select = document.getElementById('projectSelect')
        const categories = document.getElementById("categoriesSelect")
        fetch("http://34.125.160.101:8080/api/v1/core/projects")
            .then(response => response.json())
            .then(respJson => {respJson.map(project =>
            {
                state.projects.set(project.id, project.name)
                select.innerHTML += (`<option value={project.id}>${project.name}</option>`)
            }); setProjectSelect(respJson)
            });

        fetch("http://34.125.160.101:8080/api/v1/core/categories")
            .then(response => response.json())
            .then(respJson => {respJson.map(category =>
            {
                //this.state.categories.set(project.id, project.name)
                categories.innerHTML += (`<option value={category.categoryId}>${category.name}</option>`)
            });
            setCategoriesSelect(respJson)});
    }, [])

    const state = {
        projects:new Map()
    }
    const sendData= (e) =>
    {
        e.preventDefault();
        console.log(`
            {
            "description":"${description}",
            "name":"${name}",
            "deadline":"${target}",
            "projectId":"${projectSelect[0].id}",
            "categoryDtoList":[${categoriesSelect[0].categoryId}],
            "user":${user.username}
            }
            `)

        fetch(`http://34.125.160.101:8080/api/v1/core/tasks`, {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: `
            {
            "description":"${description}",
            "name":"${name}",
            "deadline":"${target}",
            "projectId":"${projectSelect[0].id}",
            "categoryDtoList":[{"id" : "${categoriesSelect[0].categoryId}"}],
            "user":"${user.username}"
            }
            `
        }).then(response => {
            if(response.status/100<3) {
                alert("Task added successfully");
                setUpdate(response.status)
            }
            else
            {
                console.log(response)
            }
        })
            .catch(e => alert(e))
    }
        return(
        <form className={"AddTaskForm"}>
            <div className={'new-todo-form'}>
                <div>
                    <input name={"name"} placeholder={'Name'} id = {'name'}  onChange={(e) => setName(e.target.value)}/>
                    <input name={"description"} placeholder={'Description'} id = {'description'}  onChange={(e) => setDescription(e.target.value)}/>
                </div>
                <div>
                    <label htmlFor={'target_time'}>Deadline</label>
                    <input type={"datetime-local"} id = {'target_time'}  onChange={(e) => setTarget(e.target.value)}/>
                </div>
                <div className={'new-todo-selectors'}>
                    <span>Choose project:</span>
                    <select id = "projectSelect"  onChange={(e) => setProjectSelect(e.target.value.id)}></select>

                    <span>Choose main category:</span>
                    <select id = "categoriesSelect"  onChange={(e) => setCategoriesSelect(e.target.value)}></select>
                </div>
                <div>
                    <Button className={'btn-primary'} type = {"submit"} onClick={sendData}> Submit</Button>
                </div>
            </div>
        </form>)
});

export default AddToProjectForm