import React, {Component} from "react";
import "./App.css"
import TaskCard from "./TaskCard";

class TaskList extends Component {
    state = {
        tasks:[],
        categories:new Map()
    }

    componentDidMount() {
        fetch("http://34.125.160.101:8080/api/v1/core/categories")
            .then(response => response.json())
            .then(respJson => respJson.map(category => this.state.categories.set(category.id, category.name)))
    const headers = { 'Authorization': 'Bearer my-token' };
        fetch('http://34.125.160.101:8080/api/v1/core/tasks',  { headers })
            .then(response => response.json())
            .then(respJson => this.setState({tasks:respJson}))
    }

    render() {
        return <div className={"TaskContainer"}> {this.state.tasks.map(task =><TaskCard />)}
        </div>
    }
}
