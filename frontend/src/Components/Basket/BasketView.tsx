import { Button, Divider } from "@mui/material";
import * as React from "react";
import BasketItem from "../../Model/Basket";
import BasketItemView from "./BasketItemView";
import { useNavigate } from "react-router-dom";

export default function BasketView() {
  const navigate = useNavigate();

  const dummyItem = {
    name: "Necrozma GX",
    description:
      "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Error, ipsum dolor eaque distinctio beatae ex dolorem quas atque id est dicta sequi culpa magni accusamus voluptatibus perspiciatis eveniet facere rerum, molestias totam non soluta voluptate tempore deleniti! Maiores voluptates ex perferendis. Officiis, at qui? Accusamus minima animi beatae officia iure? Necessitatibus recusandae blanditiis, soluta illo alias excepturi maiores eius rem labore voluptatum fugit quod aut doloremque atque laudantium dignissimos, debitis delectus quas dolor corrupti quos a minus. Provident molestiae fuga, ea illum esse eaque facilis itaque modi sequi animi quia odit reprehenderit aliquam id vitae velit? Tenetur saepe velit nam?",
    img: "https://images.pokemontcg.io/sm5/90.png",
    quantity: 1,
    price: 149.99,
  } as BasketItem;

  const items = [dummyItem, dummyItem, dummyItem];

  return (
    <div className="basket-container">
      <h1>Basket</h1>
      {items.map((item) => (
        <BasketItemView key={item.id} item={item}></BasketItemView>
      ))}
      <div className="order-sum-container">
        <span className="order-sum-label">Sum</span>
        <Divider />
        <span className="order-sum-value">
          {items.map((v) => v.price * v.quantity).reduce((a, b) => a + b)} €
        </span>
        <Button variant="contained" onClick={() => navigate("/checkout")}>
          Checkout
        </Button>
      </div>
    </div>
  );
}
