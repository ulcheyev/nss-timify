import logo from './logo.svg';
import './App.css';
import {Component} from "react";

class App extends Component {
  state = {
    clients: []
  };

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/api/v1/system/users');
    const body = await response.json();
    this.setState({clients: body});
  }

  render() {
    const {clients} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Clients</h2>
              {clients.map(client =>
                  <div key={client.id}>
                    {client.username}
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}
export default App;
