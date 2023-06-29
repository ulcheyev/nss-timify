import axios from "axios";

const API_URL = 'http://localhost:8080/api/v1/notifications'; //TODO change to 34.125.160.101
class NotificationService {

    async sendEmail(id, text, email) {
        try {
            let jsonToSend =
            {
                userId:id,
                text:text,
                email:email
            }
            return  await axios.post(API_URL + '/email',
                jsonToSend,
                {
                    headers: {'Content-Type': 'application/json'}
                }
            );
        }catch (err) {
            return err;
        }
    }
}

export default new NotificationService()