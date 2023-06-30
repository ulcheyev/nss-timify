import {AUTH_ROUTE, PROJECT_ROUTE, TODO_ROUTE, USER_ROUTE} from "./utils/consts";
import Auth from "./pages/Auth";
import ToDo from "./pages/ToDo";
import Profile from "./pages/Profile";


export const authRoutes = [
    {
        path: USER_ROUTE,
        Component: Profile
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
    }
]