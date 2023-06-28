import Project from "./pages/Project";
import {ADMIN_ROUTE, AUTH_ROUTE, PROJECT_ROUTE, REGISTRATION_ROUTE, TODO_ROUTE} from "./utils/consts";
import Auth from "./pages/Auth";
import ToDo from "./pages/ToDo";
import Admin from "./pages/Admin";

export const authRoutes = [
    {
        path: ADMIN_ROUTE,
        Component: Admin
    },
    {
        path: PROJECT_ROUTE + '/:id',
        Component: Project
    },
    {
        path: TODO_ROUTE,
        Component: ToDo
    },
]

export const publicRoutes = [

    {
        path: AUTH_ROUTE,
        Component: Auth
    },
    {
        path: REGISTRATION_ROUTE,
        Component: Auth
    },
]