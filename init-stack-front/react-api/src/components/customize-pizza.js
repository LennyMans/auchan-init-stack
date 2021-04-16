import React from "react";
import ReactDOM from "react-dom";

class CustomizePizza extends React.Component {
  constructor() {
    super();
    this.state = { data: [] };
  }

  render() {
    return (
      <div class="app__customize">
        <h2>Je customise ma pizza</h2>
        <form>
          <div class="from-group">
            <label for="commande">Saissisez votre numéro de commande</label>
            <input type="text" class="form-control" id="commande" placeholder="exemple : d0b9da8d-304d-4a41-a24c-cc72094182d0"></input>
          </div>
          <div class="form-group">
            <label for="pizza">Pizza</label>
            <select class="form-control" id="pizza">
              <option>cheese</option>
              <option>vegan</option>
              <option>vegetarian</option>
              <option>meat</option>
            </select>
          </div>
          <div class="form-group">
            <label for="size">Taille</label>
            <select class="form-control" id="size">
              <option>m</option>
              <option>l</option>
              <option>xl</option>
            </select>
          </div>
          <div class="form-group">
            <label for="spicy">Sauce piquante ?</label>
            <select class="form-control" id="spicy">
              <option>true</option>
              <option>false</option>
            </select>
          </div>
          <button class="btn btn-primary" type="submit">Je valide mon choix</button>
        </form>
      </div>
    );
  }
}

ReactDOM.render(<CustomizePizza />, document.getElementById("root"));

export default CustomizePizza;
