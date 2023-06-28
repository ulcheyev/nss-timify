import React, {useContext, useEffect} from 'react';
import {observer} from "mobx-react-lite";
import {Col, Container} from "reactstrap";
import {Context} from "../index";
import TaskList from "../components/TaskList";

const ToDo = observer(()=> {

    const {task} = useContext(Context)

    return (
        <Container>
            <Col>
                <TaskList/>
            </Col>
        </Container>

    );
});

export default ToDo;