import React, {Component, useContext} from "react";
import TaskCard from "./TaskCard";
import {Context} from "../index";

class TaskList extends Component
{

    state = {
        tasks:[],
        categories:new Map()
    }


    componentDidMount() {
        const user =  this.context
        fetch("http://localhost:8080/api/v1/core/categories")// TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => respJson.map(category => this.state.categories.set(category.id, category.name)))
        const headers = {Authorization: 'Bearer ' + user.token};
        fetch('http://localhost:8080/api/v1/core/tasks', {headers})// TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => this.setState({tasks:respJson}))
    }

    render()
    {
        return <div className={"TaskContainer"}>
            {this.state.tasks.map(task =>
                <TaskCard key={task.id} task={task}/>)
            }
        </div>
    }
}
export default TaskList;