import React from 'react';
import {observer} from "mobx-react-lite";

const ToDo = observer(()=> {



    return (
        <section>
            <h1>Стоматолог: - Не бойся Серёга, все бывает в первый раз!
                Пациент: - Я не Серёга.
                Стоматолог: - Я знаю. Серёга это я... </h1>
        </section>
    );
});

export default ToDo;