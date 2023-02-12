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

export default function AddToBasket({ price }: { price: number }) {
  const [quantity, setQuantity] = useState(1);

  const quantityChange = (e) => {
    setQuantity(e.target.value);
  };

  return (
    <div>
      <span className="detail-price">{formatPrice(price)}</span>
      <div className="add-basket-group">
        <Button variant="contained" sx={{ height: 40 }}>
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
