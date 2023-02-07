import * as React from "react";
import Header from "../Components/Header/Header";
import { useLocation } from "react-router-dom";
import ProductDetail from "../Components/ProductDetail";
import RecommendedProducts from "../Components/RecommendedProducts";
import Card from "../Model/Card";
import "../CSS/ProductDetails.css";

export default function ProductDetails() {
  const { state } = useLocation();
  const card: Card = {
    id: state.id, // default werte beachten
    name: state.name,
    price: state.price,
    description: state.description,
    condition: state.condition,
    pokemonId: state.pokemonId,
  };

  return (
    <div>
      <Header />
      <ProductDetail {...card} />
      <RecommendedProducts />
    </div>
  );
}
