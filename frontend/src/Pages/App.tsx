import * as React from "react";
import Header from "../Components/Header/Header";
import ContentBanner from "../Components/ContentBanner";
import RecommendedProducts from "../Components/Product/RecommendedProducts";
import OpeningText from "../Components/OpeningText";
import "../Css/App.css";

export default function App() {
  return (
    <div>
      <Header />
      <ContentBanner />
      <OpeningText />
      <RecommendedProducts />
    </div>
  );
}
