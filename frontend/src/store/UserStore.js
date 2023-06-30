import {makeAutoObservable} from "mobx";


const IS_ADMIN_URL = 'http://localhost:8080/api/v1/system/users/is-admin?tok='
export default class UserStore {
    constructor() {
        this._isAuth = false
        this._token = ""
        this._user = {}
        this._username = ""
        this._isAdmin = false
        makeAutoObservable(this)
    }
    get isAdmin() {
        return this._isAdmin;
    }

    setIsAdmin(value) {
        this._isAdmin = value;
    }

    get username() {
        return this._username;
    }

    setUsername(value) {
        this._username = value;
    }

    setIsAuth(bool) {
        this._isAuth = bool
    }

    setToken(token) {
        this._token = token
        fetch(IS_ADMIN_URL + this.token)
            .then(res => console.log(res.body.getReader()))
            .catch(e => alert(e))
    }

    setUser(user) {
        this._user = user
    }

    get isAuth() {
        return this._isAuth
    }

    get token(){
        return this._token
    }

    get user() {
        return this._user
    }
}