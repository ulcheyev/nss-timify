import React, {useEffect, useState} from "react";
import TaskCard from "./TaskCard";
import Cookies from 'js-cookie';
import {Button, Container, Row} from "reactstrap";
import {observer} from "mobx-react-lite";

export const ListContext = React.createContext(null)

const TaskList = observer(() => {
    let count= 0
    const pageSize = 5;
    const [pages, setPages] = useState([])
    const [currPage, setCurrPage] = useState(0);

    const [tasks, setTasks] = useState([])

    const [render, setRender] = useState(0)

    const categories = new Map()


    const pageCount = async () => {
        let allPages = []
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        await fetch('http://34.125.160.101:8080/api/v1/core/tasks/count', {headers})
            .then(response => response.json()).then(respJson => count = respJson)
        for (let i = 0; i < Math.ceil((count/pageSize)); i++) {
            allPages.push(i)
        }
        setPages(allPages)
    }

    useEffect(async () => {
        await pageCount()
        await fetch("http://34.125.160.101:8080/api/v1/core/categories")
            .then(response => response.json())
            .then(respJson => respJson.map(category => categories.set(category.categoryId, category.name)))
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        await fetch(`http://34.125.160.101:8080/api/v1/core/tasks?page=${currPage}&size=${pageSize}`, {headers})
            .then(response => response.json())
            .then(respJson => {
                setTasks(respJson)
            })
    }, [render, currPage])


    return (
        <Container className={"TaskContainer"} style={{color: 'white'}}>
            <div className={"TaskList"}>
                {tasks.length > 0 && tasks.map(task => task.status !== "ARCHIVED" &&
                    (<TaskCard key={task.id} task={task} categories={categories}/>)
                )}
            </div>
            <Row className="Pagination mt-3">
                {pages.length >0 ? (pages.map(page =>
                    <Button  active={currPage === page}
                            className={'btn-primary'}
                            key={page}
                            onClick={() => setCurrPage(page)}
                    >
                        {page+1}
                    </Button>
                ))
                :
                (
                    <div style={{color : "#030303"}}>No Tasks yet</div>
                )}
            </Row>
        </Container>
    )
});
export default TaskList;