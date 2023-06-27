import React from 'react';

const TaskCard = ({task}) => {
    return (
        <div className={"Task"} key={task.id} id={task.id}>
            <div className={"UpperLine"}>
                <div className={"TaskHead"}>
                    {task.name}
                </div>
                <div>Deadline:</div>
            </div>
            <div className={"LowerLine"}>
                <p className={"Descritpion"}>
                    {task.description}
                </p>
                <div className={"Categories"}>
                    Categories:
                    {task.categoryDtoList.map(element =>
                        <div>
                            {this.state.categories.has(element.id) ? this.state.categories.get(element.id) : "Unknown category"}
                        </div>)}
                </div>
            </div>
        </div>
    );
};

export default TaskCard;