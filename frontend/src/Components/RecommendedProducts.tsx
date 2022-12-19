import * as React from "react";
import CardView from "./Product/CardView";
import Box from "@mui/material/Box";
import { useEffect, useState } from "react";
import Card from "../Model/Card";
import { useNavigate } from "react-router-dom";

export default function RecommendedProducts() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const productList = async () => {
      const data = await fetch("http://localhost:8080/v1/products?amount=8", {
        method: "GET",
      });
      const jsonData = await data.json();
      return jsonData.map((data: any) => {
        const card: Card = {
          id: data.id,
          name: data.name,
          price: data.price,
          description: data.description,
          condition: data.condition,
          pokemonId: data.pokemonId,
        };
        return card;
      });
    };

    productList().then((result) => setProducts(result));
  }, []);

  const navigate = useNavigate();
  const routeChange = (card: Card) => {
    const path = `/details`;
    navigate(path, { state: card });
  };

  return (
    <Box className="recommended-products">
      <Box className="header disable-select">
        <h1>Recommended Products</h1>
      </Box>
      <Box className="card-list">
        {products.map((item: Card) => (
          <Box
            key={item.id}
            className="card"
            onClick={() => routeChange(item)}
            data-cy="card"
          >
            <CardView
              id={item.id}
              name={item.name}
              price={item.price}
              description={item.description}
              condition={item.condition}
              pokemonId={item.pokemonId}
            />
          </Box>
        ))}
      </Box>
    </Box>
  );
}
