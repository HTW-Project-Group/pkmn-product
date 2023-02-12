import * as React from "react";
import Box from "@mui/material/Box";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Card from "../../Model/Card";
import CardView from "./CardView";

export default function RecommendedProducts() {
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const productList = async () => {
      const data = await fetch("http://localhost:8080/v1/products?amount=8", {
        method: "GET",
      });
      const jsonData = await data.json();
      return jsonData.map((data) => {
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
        <img
          className="recommended-products-img"
          src="/img/recommended_products.jpg"
          alt="Recommended Products"
        />
        {/*<h1 className="recommended">Recommended Products</h1>*/}
      </Box>
      <Box className="card-list">
        {products.map((item: Card) => (
          <Box
            key={item.pokemonId}
            className="card"
            onClick={() => navigate(`/details/${item.pokemonId}`)}
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
