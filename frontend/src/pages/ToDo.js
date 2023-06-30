import React, {useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import {Button, Col, Container} from "reactstrap";
import TaskList from "../components/TaskList";
import AddForm from "../components/AddForm";

export const TodoContext = React.createContext(null)

const ToDo = observer(()=> {

    const [update, setUpdate] = useState(0)

    useEffect( () => {
        const btn = document.getElementById("formToggler")
        btn.addEventListener("click", formToggle)
    }, [])

    const formToggle = () => {
        const formContainer = document.getElementById("addFormContainer")
        const btn = document.getElementById("formToggler")
        if (formContainer){
            if (formContainer.className === "Hidden"){
                formContainer.className = "TaskForm"
                btn.innerText = "Cancel"
            } else {
                formContainer.className = "Hidden"
                btn.innerText = "Add Task"
            }
        }
    }

    return (
        <TodoContext.Provider value={setUpdate}>
            <Container className={'Main flex-row'}>
                <h1 style={{color: "#fea82f"}}>Your Tasks</h1>
                <Col>
                    <Button id = "formToggler" className={'btn-primary'}>
                        Add Task
                    </Button>
                    <div id = "addFormContainer" className={"Hidden"}>
                        <AddForm/>
                    </div>
                    <TaskList/>
                </Col>
            </Container>
        </TodoContext.Provider>
    );
});

export default ToDo;