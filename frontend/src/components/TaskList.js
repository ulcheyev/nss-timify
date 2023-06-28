import React from "react";
import TaskCard from "./TaskCard";
import {observer} from "mobx-react-lite";

const TaskList = observer(() => {
    const state = {
        tasks:[],
        categories:new Map()
    }

    const componentDidMount = () => {
        fetch("http://34.125.160.101:8080/api/v1/core/categories")
            .then(response => response.json())
            .then(respJson => respJson.map(category => this.state.categories.set(category.id, category.name)))
        const headers = { 'Authorization': 'Bearer my-token' };
        fetch('http://34.125.160.101:8080/api/v1/core/tasks',  { headers })
            .then(response => response.json())
            .then(respJson => this.setState({tasks:respJson}))
    }

    return (
        <div className={"taskContainer"}>
            {this.state.tasks.map(task =><TaskCard key={task.id} device={task}/>)}
        </div>
    )
});

export default TaskList;