import * as React from "react";
import Header from "../Components/Header/Header";
import { useLocation } from "react-router-dom";
import Box from "@mui/material/Box";

import ProductDetail from "../Components/ProductDetail";
import RecommendedProducts from "../Components/RecommendedProducts";
import Card from "../Components/Interfaces/Card";

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
    <Box>
      <Box>
        <Header />
      </Box>
      <Box
        margin={"9% auto"}
        minHeight={"40%"}
        maxWidth={"70%"}
        display={"flex"}
        flexWrap="wrap"
        justifyContent={"center"}
        alignContent={"center"}
        border={"1px solid black"}
      >
        <ProductDetail {...card} />
      </Box>
      <Box marginTop={"-30%"}>
        <RecommendedProducts />
      </Box>
    </Box>
  );
}
