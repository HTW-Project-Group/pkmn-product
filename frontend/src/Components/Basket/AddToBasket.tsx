import * as React from "react";
import { useState } from "react";
import {
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
} from "@mui/material";
import { formatPrice } from "../../Helper/Format";
import Card from "../../Model/Card";
import ProductDto from "../../Model/ProductDto";

export default function AddToBasket({ product }: { product: Card }) {
  const [quantity, setQuantity] = useState(1);

  const quantityChange = (e) => {
    setQuantity(e.target.value);
  };

  const addToBasketRequest = () => {
    fetch(`http://localhost:8080/v1/queue/basket/add`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userId: "00000000-0000-0000-0000-000000000000",
        pokemonId: product.pokemonId,
        name: product.name,
        description: product.description,
        quantity,
        price: product.price,
      } as ProductDto),
    });
  };

  return (
    <div>
      <span className="detail-price">{formatPrice(product.price)}</span>
      <div className="add-basket-group">
        <Button
          variant="contained"
          sx={{ height: 40 }}
          onClick={addToBasketRequest}
        >
          Add To Basket
        </Button>
        <FormControl sx={{ m: 1, minWidth: 120 }} size="small">
          <InputLabel id="quantity-select">Quantity</InputLabel>
          <Select
            labelId="quantity-select"
            id="quantity-select"
            value={quantity}
            label="Quantity"
            onChange={quantityChange}
          >
            {Array(10)
              .fill(0)
              .map((_, i) => (
                <MenuItem key={`sel-${i + 1}`} value={i + 1}>
                  {i + 1}
                </MenuItem>
              ))}
          </Select>
        </FormControl>
      </div>
    </div>
  );
}
