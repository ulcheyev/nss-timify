import logo from './logo.svg';
import './App.css';
import React from 'react'
import {Component} from "react";
import Auth from "./pages/auth";
import ProjectTaskList from "./components/ProjectTaskList";
import AddToProjectForm from "./components/AddForm";
class App extends Component {

  render() {
    return (
        <AddToProjectForm/>
    );
  }
}
export default App;
