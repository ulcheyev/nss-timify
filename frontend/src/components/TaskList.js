import React, {useEffect, useState} from "react";
import TaskCard from "./TaskCard";
import Cookies from 'js-cookie';
import {Button, Container, Row} from "reactstrap";

export const ListContext = React.createContext(null)

const TaskList = (() => {
    const [count, setCount] = useState(0)
    const pageSize = 5;
    const [pages, setPages] = useState([])
    const [currPage, setCurrPage] = useState(0);

    const [tasks, setTasks] = useState([])

    const [render, setRender] = useState(0)

    const pageCount = () => {
        let allPages = []
        let allTasks = 0
        tasks.map(task => task.status !== "ARCHIVED" && allTasks++)
        setCount(allTasks)
        for (let i = 0; i < Math.ceil((count/pageSize)); i++) {
            allPages.push(i)
        }
        setPages(allPages)
    }

    useEffect(async () => {
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        await fetch(`http://localhost:8080/api/v1/core/tasks?page=${currPage}&size=${pageSize}`, {headers}) // TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => {
                console.log(respJson)
                setTasks(respJson)
            })
        pageCount()
    }, [render, currPage])


    return (
        <ListContext.Provider value={setRender}>
            <Container className={"TaskContainer"} style={{color: 'white'}}>
                <div className={"TaskList"}>
                    {(console.log(tasks),
                        tasks.map(task => task.status !== "ARCHIVED" &&
                        ( console.log(task),
                            <TaskCard key={task.id} task={task}/>)
                    ))}
                </div>
                <Row className="Pagination mt-3">
                    {pages.map(page =>
                        <Button  active={currPage === page}
                                className={'btn-primary'}
                                key={page}
                                onClick={() => {
                                    setCurrPage(page)
                                }}
                        >
                            {page+1}
                        </Button>
                    )}
                </Row>
            </Container>
        </ListContext.Provider>
    )
});
export default TaskList;