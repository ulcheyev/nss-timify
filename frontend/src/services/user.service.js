import axios from 'axios';

const API_URL = 'http://34.125.160.101:8080/api/v1/system/'; //TODO change to 34.125.160.101

class UserService {
    async login(user) {
        try {
            let jsonToSend = JSON.stringify(user)

            return  await axios.post(API_URL + 'login',
                jsonToSend,
                {
                    headers: {'Content-Type': 'application/json'},
                    withCredentials:true
                }
            );
        }catch (err) {
            return err;
        }
    }


    async register(user){
        try {
            let jsonToSend = JSON.stringify(user)

            return  await axios.post(API_URL + 'register',
                jsonToSend,
                {
                    headers: {'Content-Type': 'application/json'},
                    withCredentials:true
                }
            );
        }catch (err) {
            return err;
        }
    }

    logout(user){

    }

}
export default new UserService();