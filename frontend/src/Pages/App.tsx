import * as React from "react";
import Header from "../Components/Header/Header";
import ContentBanner from "../Components/ContentBanner";
import RecommendedProducts from "../Components/Product/RecommendedProducts";
import "../Css/App.css";

export default function App() {
  return (
    <div>
      <Header />
      <ContentBanner />
      <div className="opening-text">
        <p>
          Welcome to the world of Pokémon Trading Cards! Here at PKMN, we are dedicated to
          bringing you the very best in Pokémon collectibles and accessories. Whether you&apos;re a seasoned
          player or just starting out, we have everything you need to build the ultimate deck and become
          a Pokémon master.
        </p>
        <p>
          Our collection features a wide variety of cards, from the latest sets to rare
          and hard-to-find treasures. With our competitive prices, fast shipping, and exceptional customer
          service, there&apos;s never been a better time to get into the world of Pokémon Trading Cards.
        </p>
        <p>
          So why wait? Start your journey today and discover the thrill of collecting and battling with the
          most iconic creatures in the Pokémon universe!
        </p>
      </div>
      <RecommendedProducts />
    </div>
  );
}
