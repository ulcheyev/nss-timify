import logo from './logo.svg';
import './App.css';
import React from 'react'
import {Component} from "react";
import Auth from "./pages/auth";
import ProjectTaskList from "./components/ProjectTaskList";
class App extends Component {

  render() {
    return (
        <ProjectTaskList/>
    );
  }
}
export default App;
