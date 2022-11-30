import "./App.css";
import * as React from "react";
import Header from "./Components/Header/Header";
import ContentBanner from "./Components/ContentBanner";
import Cards from "./Components/Cards"
import RecommendedProducts from "./Components/RecommendedProducts";



export default function App() {
  return (
    <div>
      <Header />
      <ContentBanner />
      <RecommendedProducts />
    </div>
  );
}
