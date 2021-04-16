import React from "react";
import ReactDOM from 'react-dom';

class CreatePizza extends React.Component {
    constructor() {
        super();
        this.state = { data: [] };
      }

    async newPizza() {
      console.log("Click on pizza")
      const response = await fetch("/pizzaCreation");
      const json = await response.json();
      this.setState({ data: json });
    }

    render() {
      return (
          <div class="app__order">
            <h2>Commander une pizza</h2>
            <button class="btn btn-primary" onClick={this.newPizza}>Je créé ma pizza</button>
          </div>
      );
    }
  }
  
  ReactDOM.render(<CreatePizza />, document.getElementById('root'));

export default CreatePizza;
