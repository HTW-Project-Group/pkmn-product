import * as React from "react";
import CardView from "./Product/CardView";
import Box from "@mui/material/Box";
import { useEffect, useState } from "react";
import Card from "../Model/Card";
import Typography from "@mui/material/Typography";

export default function RecommendedProducts() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const productList = async () => {
      const data = await fetch("http://localhost:8080/v1/products", {
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

  return (
    <Box className="recommended-products">
      <Box className="header disable-select">
        <h1>Recommended Products</h1>
      </Box>
      <Box className="card-list">
        {products.slice(0, 8).map((item: Card) => (
          <Box key={item.id} className="card">
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
        <Typography color="#f00">
          TODO: Hier in RecommendedProducts.tsx products.slice() entfernen, wenn
          Backend für Recommended Products steht
        </Typography>
      </Box>
    </Box>
  );
}
