import {makeAutoObservable} from "mobx";

export default class UserStore {
    constructor() {
        this._isAuth = false
        this._token = ""
        this._user = {}
        this._username = ""
        makeAutoObservable(this)
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

    setToken(token){
        this._token = token
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