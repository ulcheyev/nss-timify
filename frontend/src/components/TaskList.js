import React, {useEffect, useState} from "react";
import TaskCard from "./TaskCard";
import Cookies from 'js-cookie';
import {Button, Container, Row} from "reactstrap";
import {observer} from "mobx-react-lite";

export const ListContext = React.createContext(null)

const TaskList = observer(() => {
    const [count, setCount] = useState(0)
    const pageSize = 5;
    const [pages, setPages] = useState([])
    const [currPage, setCurrPage] = useState(0);

    const [tasks, setTasks] = useState([])

    const [render, setRender] = useState(0)

    const categories = new Map()


    const pageCount = async () => {
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        let allPages = []
        await fetch('http://localhost:8080/api/v1/core/tasks/count', {headers})
            .then(response => response.json()).then(respJson => setCount(respJson))
        for (let i = 0; i < Math.ceil((count / pageSize)); i++) {
            allPages.push(i)
        }
        setPages(allPages)
    }

    useEffect(async () => {
        await fetch("http://localhost:8080/api/v1/core/categories") // TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => respJson.map(category => categories.set(category.categoryId, category.name)))
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        await fetch(`http://localhost:8080/api/v1/core/tasks?page=${currPage}&size=${pageSize}`, {headers}) // TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => {
                console.log(respJson)
                setTasks(respJson)
            })
        await pageCount()
    }, [render, currPage])


    return (
        <ListContext.Provider value={setRender}>
            <Container className={"TaskContainer"} style={{color: 'white'}}>
                <div className={"TaskList"}>
                    {(console.log(tasks),
                        tasks.map(task =>
                            <TaskCard key = {task.id} task={task} categories = {categories}/>)
                    )}
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