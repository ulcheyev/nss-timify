import React, {Component} from "react";
import "./TaskList.css"
import TaskList from "./TaskList";
class ProjectTaskList extends TaskList
{
    state = {
        tasks:[],
        categories:new Map()
    }


    componentDidMount() {
        const queryParams = new URLSearchParams(window.location.search)
        const id = queryParams.get("project")
        console.log(id)
        fetch("http://localhost:8080/api/v1/core/categories")
            .then(response => response.json())
            .then(respJson => respJson.map(category => this.state.categories.set(category.id, category.name)))
        fetch(`http://localhost:8080/api/v1/core/projects/${id}/tasks`)
            .then(response => response.json())
            .then(respJson => this.setState({tasks:respJson}))
    }

}
export default ProjectTaskList;