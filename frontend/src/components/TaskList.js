import React, {Component, useContext} from "react";
import TaskCard from "./TaskCard";
import Cookies from 'js-cookie';

class TaskList extends Component
{

    state = {
        tasks:[],
        categories:new Map()
        }


    async componentDidMount() {
        await fetch("http://localhost:8080/api/v1/core/categories")// TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => respJson.map(category => this.state.categories.set(category.categoryId, category.name)))
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        await fetch('http://localhost:8080/api/v1/core/tasks', {headers})// TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => this.setState({tasks: respJson}))
        console.log(this.state.categories)
    }

    render()
    {
        return <div className={"TaskContainer"}>
            {this.state.tasks.map(task =>
                <TaskCard key={task.id} task={task} categories = {this.state.categories}/>)
            }
        </div>
    }
}
export default TaskList;