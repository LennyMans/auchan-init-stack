import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import React, {Component} from 'react';
import Pizzas from './components/pizzas';


class App extends Component {
  render() {
    return ( 
      <Pizzas pizzas={this.state.pizzas} />
    );
  }

  state = {
      pizzas: []
  };

  componentDidMount() {
    fetch("/pizzas")
        .then(res => res.json())
        .then((data) => {
            this.setState({ pizzas: data })
            console.log(data);
        })
        .catch(console.log)
  }
}

export default App;
