import React from 'react'

const Pizzas = ({ pizzas }) => {
  return (
    <div>
        <div>
          <center><h1>Pizza List</h1></center>
          {pizzas.map((pizza) => (
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">{pizza.id}</h5>
                <p class="card-text">{pizza.flavor}</p>
                <p class="card-text">{pizza.size}</p>
                <p class="card-text">{pizza.spicy}</p>
              </div>
            </div>
          ))}
        </div>

    </div>
  )
};

export default Pizzas