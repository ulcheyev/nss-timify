import React, {useEffect, useState} from "react";
import TaskCard from "./TaskCard";
import Cookies from 'js-cookie';
import {Button, Container, Pagination, Row} from "reactstrap";

const TaskList = (() => {
    const [count, setCount] = useState(0)
    const pageSize = 5;
    const [pages, setPages] = useState([])
    const [currPage, setCurrPage] = useState(0);

    const [tasks, setTasks] = useState([])

    const categories = new Map()


    const pageCount = async () => {
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        let allPages = []
        await fetch('http://34.125.160.101:8080/api/v1/core/tasks/count', {headers}) //TODO
            .then(response => response.json()).then(respJson => setCount(respJson))
        for (let i = 0; i < Math.ceil((count/pageSize)); i++) {
            allPages.push(i)
        }
        setPages(allPages)
    }

    useEffect(async () => {
        await pageCount()
        await fetch("http://34.125.160.101:8080/api/v1/core/categories") // TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => respJson.map(category => categories.set(category.categoryId, category.name)))
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        await fetch(`http://34.125.160.101:8080/api/v1/core/tasks?page=${currPage}&size=${pageSize}`, {headers}) // TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => {
                setTasks(respJson)
                {tasks.map(task => task.status === "ARCHIVED" && setCount(count - 1))}
            })
    }, [currPage, count])


    return (
        <Container className={"TaskContainer"} style={{color: 'white'}}>
            <div className={"TaskList"}>
                {tasks.map(task => task.status !== "ARCHIVED" &&
                    (<TaskCard key={task.id} task={task} categories={categories}/>)
                )}
            </div>
            <Row className="Pagination mt-3">
                {pages.map(page =>
                    <Button  active={currPage === page}
                            className={'btn-primary'}
                            key={page}
                            onClick={() => setCurrPage(page)}
                    >
                        {page+1}
                    </Button>
                )}
            </Row>
        </Container>
    )
});
export default TaskList;