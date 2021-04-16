import './App.scss';
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import React, { Component } from "react";
import Pizzas from "./components/pizzas";
import CreatePizza from "./components/create-pizza";
import CustomizePizza from "./components/customize-pizza";

class App extends Component {
  render() {
    return (
      <div class="app">
        <Pizzas pizzas={this.state.pizzas} />
        <div class="app__command">
          <CreatePizza createpizza={this.state.createpizza} />
          <CustomizePizza />
        </div>
      </div>
    );
  }

  state = {
    pizzas: []
  };

  async componentDidMount() {
    const response = await fetch("/pizzas");
    const json = await response.json();
    this.setState({ pizzas: json });
    console.log(json)
  }
}

export default App;
