import * as React from "react";
import { Box } from "@mui/material";
import { useCallback, useEffect, useRef, useState } from "react";
import Card from "../../Model/Card";
import CardView from "../Product/CardView";
import { useNavigate, useParams } from "react-router-dom";
import { useMutationObservable } from "../../Helper/UseMutationObservable";

function ResultRenderer({ products }: { products: Card[] }) {
  const navigate = useNavigate();

  if (products.length > 0) {
    return (
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
    );
  } else {
    return (
      <Box className="no-results">
        <p>Nothing was found</p>
      </Box>
    );
  }
}

export default function SearchResults() {
  const { query } = useParams();
  const queryRef = useRef();
  const [products, setProducts] = useState([]);

  const productList = async (q) => {
    const data = await fetch(
      `http://localhost:8080/v1/product/search/name%3A${q}`,
      {
        method: "GET",
      }
    );
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

  const onQueryMutation = useCallback(
    (queryRef) => {
      const q = queryRef[0]?.target?.defaultValue;
      productList(q).then((result) => setProducts(result));
    },
    [setProducts]
  );

  useMutationObservable(queryRef.current, onQueryMutation);

  useEffect(() => {
    productList(query).then((result) => setProducts(result));
  }, []);

  return (
    <div>
      <input
        className="search-query"
        ref={queryRef}
        defaultValue={query}
      ></input>
      <h1 className="search-header">Search results for &quot;{query}&quot;</h1>

      <Box className="search-result-container">
        <ResultRenderer products={products} />
      </Box>
    </div>
  );
}
