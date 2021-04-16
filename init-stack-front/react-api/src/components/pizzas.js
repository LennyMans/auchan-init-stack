import React from "react";

const Pizzas = ({ pizzas }) => {
  return (
    <div>
    <h2>Liste des pizza</h2>
      <div class="app__pizzas d-flex">
        {pizzas.map((pizza) => (
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">{`Commande numéro ${pizza.id}`}</h5>
              <p class="card-text">{`Pizza : ${pizza.flavor}`}</p>
              <p class="card-text">{`Taille : ${pizza.size}`}</p>
              <p class="card-text">{`Sauce piquante : ${pizza.spicy}`}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Pizzas;
