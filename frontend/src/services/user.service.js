import axios from 'axios';

const API_URL = 'http://34.125.160.101:8080/api/v1/system/';

export class UserService {
    login(user){
        console.log(user)
    }

    register(user){

    }

    logout(user){

    }

}
export default new UserService();