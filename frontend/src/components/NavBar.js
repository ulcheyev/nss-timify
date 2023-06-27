import React, {useContext} from 'react';
import {Container, Nav, Navbar} from "react-bootstrap";
import {AUTH_ROUTE, TODO_ROUTE, USER_ROUTE} from "../utils/consts";
import {Context} from "../index";
import {Button, Row} from "reactstrap";
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
        <Navbar bg="warning" data-bs-theme="dark">
            <Container>
                { user.isAuth ?
                    <Nav  className="mr-auto" style={{color: 'white'}}>
                        <Nav.Link style={{color:'white'}} to = {TODO_ROUTE}>Timify</Nav.Link>
                        <Button variant={"outline-light"} onClick = {() => history.push(TODO_ROUTE)}> All Todos</Button>
                        <Button variant={"outline-light"} onClick = {() => history.push(USER_ROUTE)}>Profile</Button>
                        <Button variant={"outline-light"} onClick = {() => logOut()}>Log Out</Button>
                    </Nav>
                    :
                    <Nav  className="mr-auto" style={{color: 'white'}}>
                            <Nav.Link style={{color:'white'}} to = {AUTH_ROUTE}>Timify</Nav.Link>
                            <Button variant={"outline-light"} onClick = {() => history.push(AUTH_ROUTE)}>Log Out</Button>
                    </Nav>
                }
            </Container>
        </Navbar>
    );
});

export default NavBar;