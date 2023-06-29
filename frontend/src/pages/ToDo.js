import React, {useEffect} from 'react';
import {observer} from "mobx-react-lite";
import {Button, Col, Container} from "reactstrap";
import TaskList from "../components/TaskList";
import AddForm from "../components/AddForm";

const ToDo = observer(()=> {


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
        <Container className={'flex-row'}>
            <Col>

            </Col>
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

    );
});

export default ToDo;