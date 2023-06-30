import React, {useContext} from 'react';
import {Switch, Route, Redirect } from 'react-router-dom'
import {observer} from "mobx-react-lite";
import {Context} from "../index.js";
import {authRoutes, publicRoutes} from "../routes";
import {AUTH_ROUTE} from "../utils/consts";

const AppRouter = observer(() => {
    const {user} = useContext(Context)

    console.log(user)
    return (
        <Switch>
            {user.isAuth && authRoutes.map(({path, Component}) =>
                <Route key={path} path={path} component={Component} exact/>
            )}
            {publicRoutes.map(({path, Component}) =>
                <Route key={path} path={path} component={Component} exact/>
            )}
            <Redirect to ={AUTH_ROUTE} />
        </Switch>
    );
});

export default AppRouter;