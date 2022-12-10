import * as React from "react";
import Cards from "./Cards";
import Box from "@mui/material/Box";
import { useEffect, useState } from "react";
import Typography from "@mui/material/Typography";
import Card from "./Interfaces/Card";
import { useNavigate } from "react-router-dom";

export default function RecommendedProducts() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const productList = async () => {
      const data = await fetch("http://localhost:8080/v1/products", {
        method: "GET",
      });
      const jsonData = await data.json();
      const result = jsonData.map((data: any) => {
        const card: Card = {
          id: data.id, // default werte beachten
          name: data.name,
          price: data.price,
          description: data.description,
          condition: data.condition,
          pokemonId: data.pokemonId,
        };
        return card;
      });
      return result;
    };

    productList().then((result) => {
      console.log(result);
      setProducts(result);
    });
  }, []);

  const navigate = useNavigate();
  const routeChange = (card: Card) => {
    const path = `/details`;
    navigate(path, { state: card });
  };

  return (
    <Box marginLeft="3%" marginRight="3%" marginTop="40%">
      <Box sx={{ backgroundColor: "#1976D2", borderRadius: 2 }}>
        <Typography
          marginBottom="5%"
          variant="h5"
          color="white"
          textAlign="center"
        >
          Recommended Products
        </Typography>
      </Box>
      <Box marginLeft="5%" marginRight="5%" maxWidth="90%">
        <Box
          display="flex"
          flexWrap="wrap"
          gap="5%"
          maxHeight="100%"
          flexDirection="row"
          justifyContent="space-evenly"
          rowGap="10%"
        >
          {products.map((item: Card) => (
            <Box
              marginBottom="5%"
              flex="1 1 16%"
              key={item.id}
              maxHeight="20%"
              maxWidth="30%"
              borderRadius="10"
              borderColor="black"
              onClick={() => routeChange(item)}
            >
              <Cards
                key={item.id}
                name={item.name}
                id={item.id}
                price={item.price}
                description={item.description}
                condition={item.condition}
                pokemonId={item.pokemonId}
                image="https://images.pokemontcg.io/xy1/1_hires.png"
              />
            </Box>
          ))}
        </Box>
      </Box>
    </Box>
  );
}
