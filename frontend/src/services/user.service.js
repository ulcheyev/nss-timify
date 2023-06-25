import axios from 'axios';

const API_URL = 'http://34.125.160.101:8080/api/v1/system/';

class UserService {
    async login(user) {
        let us = JSON.stringify(user)
        const response = await axios.post(API_URL + 'login', us,
            {
                headers: {'Content-Type': 'application/json'},
                withCredentials: true
            })
        console.log(response?.data)
    }

    register(user){

    }

    logout(user){

    }

}
export default new UserService();