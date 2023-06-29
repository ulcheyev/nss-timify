import React, {useEffect, useState} from "react";
import TaskCard from "./TaskCard";
import Cookies from 'js-cookie';
import {Button, Pagination, Row} from "reactstrap";
import {observer} from "mobx-react-lite";

const TaskList = observer(() => {
    const [count, setCount] = useState(0);
    const pageSize = 10;
    const pages = []
    const [currPage, setCurrPage] = useState(0);

    const [tasks, setTasks] = useState([])

    const categories = new Map()


    const pageCount = async () => {
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        await fetch('http://localhost:8080/api/v1/core/tasks/count', {headers})
            .then(response => response.json()).then(respJson => setCount(respJson))
        console.log(count)
        for (let i = 0; i < count/pageSize; i++) {
            pages.push(i + 1)
        }
    }

    useEffect(async () => {
        await pageCount()
        await fetch("http://localhost:8080/api/v1/core/categories") // TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => respJson.map(category => categories.set(category.categoryId, category.name)))
        const headers = {Authorization: 'Bearer ' + Cookies.get('jwtToken')};
        await fetch(`http://localhost:8080/api/v1/core/tasks?page=${currPage}&size=${pageSize}`, {headers}) // TODO change to 34.125.160.101
            .then(response => response.json())
            .then(respJson => setTasks(respJson))
        //console.log(state.tasks)
        //console.log(state.categories)
    }, [currPage])


    return (
        <div className={"TaskContainer"} style={{color: 'white'}}>
            {tasks.map(task => <TaskCard key={task.id} task={task} categories={categories}/>)}
            { count/pageSize > 1 ?
                (<span className="mt-3">
                {pages.map(page =>
                    <Button className={'btn-primary'}
                        key={page}
                        active={currPage === page}
                        onClick={() => setCurrPage(page)}
                    >
                        {page}
                    </Button>
                )}
            </span>)
            :
                (<div></div>)
            }
        </div>
    )
});
export default TaskList;