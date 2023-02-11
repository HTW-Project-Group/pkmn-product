import * as React from "react";
import Header from "../Components/Header/Header";
import RecommendedProducts from "../Components/RecommendedProducts";
import "../Css/SearchResults.css";
import SearchResults from "../Components/Search/SearchResults";

export default function SearchResultPage() {
  return (
    <div>
      <Header />
      <SearchResults />
      <RecommendedProducts />
    </div>
  );
}
