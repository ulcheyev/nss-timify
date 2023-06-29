import React, {useContext} from 'react';
import {Nav, Navbar} from "react-bootstrap";
import {AUTH_ROUTE, TODO_ROUTE, USER_ROUTE} from "../utils/consts";
import {Context} from "../index";
import {Button, Container, Row} from "reactstrap";
import {useHistory} from "react-router-dom";
import {observer} from "mobx-react-lite";

const NavBar = observer(() => {
    const {user} = useContext(Context)
    const history = useHistory()

    const logOut = () => {
        user.setUser({})
        user.setIsAuth(false)
        history.push(AUTH_ROUTE)
    }

    return (

        <Navbar data-bs-theme="dark">
            <Container>
                <Nav.Link className={"appName"} style={{color:'white'}}  sticky="top" to = {TODO_ROUTE}>
                    <img src="../logo_timify.png" alt=""/>
                    Timify
                </Nav.Link>
                { user.isAuth ?
                    <Nav variant={"underline"} className="flex-row">
                            <Nav.Link style={{color:'white'}} onClick = {() => history.push(TODO_ROUTE)}> All Todos</Nav.Link >
                            <Nav.Link style={{color:'white'}} onClick = {() => history.push(USER_ROUTE)}>Profile</Nav.Link >
                            <Nav.Link style={{color:'white'}} onClick = {() => logOut()}>Log Out</Nav.Link >
                    </Nav>
                    :
                    <Nav>
                        <Nav.Link  onClick = {() => history.push(AUTH_ROUTE)}>Log Out</Nav.Link >
                    </Nav>
                }
            </Container>
        </Navbar>
    );
});

export default NavBar;