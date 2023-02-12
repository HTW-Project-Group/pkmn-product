import * as React from "react";
import Header from "../Components/Header/Header";
import { useParams } from "react-router-dom";
import ProductDetail from "../Components/Product/ProductDetail";
import RecommendedProducts from "../Components/Product/RecommendedProducts";
import "../Css/ProductDetails.css";

export default function ProductDetailPage() {
  const { id } = useParams();

  return (
    <div>
      <Header />
      <ProductDetail pokemonId={id} />
      <RecommendedProducts />
    </div>
  );
}
