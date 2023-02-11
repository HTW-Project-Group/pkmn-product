import * as React from "react";
import Header from "../Components/Header/Header";
import RecommendedProducts from "../Components/Product/RecommendedProducts";
import BasketView from "../Components/Basket/BasketView";
import "../Css/Basket.css";

export default function BasketPage() {
  return (
    <div>
      <Header />
      <BasketView />
      <RecommendedProducts />
    </div>
  );
}
