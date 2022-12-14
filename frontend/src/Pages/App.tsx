import * as React from "react";
import Header from "../Components/Header/Header";
import ContentBanner from "../Components/ContentBanner";
import RecommendedProducts from "../Components/RecommendedProducts";
import "../App.css"

export default function App() {
  return (
    <div>
      <Header />
      <ContentBanner />
      <RecommendedProducts />
    </div>
  );
}
