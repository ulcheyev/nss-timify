import {makeAutoObservable} from "mobx";

export default class TaskStore {
    constructor() {

        makeAutoObservable(this)
    }
}