import {makeAutoObservable} from "mobx";


const IS_ADMIN_URL = 'http://34.125.160.101:8080/api/v1/system/users/is-admin?tok='
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

    async setToken(token) {
        this._token = token
        await fetch(IS_ADMIN_URL + this.token)
            .then(res => res.json().then(r => this.setIsAdmin(r.ex)))
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