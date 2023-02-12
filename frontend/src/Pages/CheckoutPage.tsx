import * as React from "react";
import Header from "../Components/Header/Header";
import RecommendedProducts from "../Components/Product/RecommendedProducts";
import "../Css/Checkout.css";
import CheckoutView from "../Components/Checkout/CheckoutView";

export default function CheckoutPage() {
  return (
    <div>
      <Header />
      <CheckoutView />
      <RecommendedProducts />
    </div>
  );
}
