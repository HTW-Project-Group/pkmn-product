import * as React from "react";
import BasketItem from "../../Model/Basket";
import {
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import { formatPrice, formatWithMaxLen } from "../../Helper/Format";
import { useState } from "react";

export default function BasketItemView({ item }: { item: BasketItem }) {
  const [quantity, setQuantity] = useState(item.quantity);

  const quantityChange = (e, item) => {
    setQuantity(e.target.value);
  };

  return (
    <div className="basket-item">
      <div className="basket-item-img">
        <img src={item.img} alt="Pokemon Image" />
      </div>
      <div className="basket-item-content">
        <h2>{item.name}</h2>
        <p>{formatWithMaxLen(item.description, 300)}</p>
        <div style={{ flexGrow: 1 }}></div>
        <div className="basket-item-actions">
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
          <Button
            variant="outlined"
            color="error"
            startIcon={<DeleteIcon />}
            sx={{ height: 40 }}
          >
            Remove
          </Button>
        </div>
      </div>
      <div className="basket-item-price">
        <span className="price">{formatPrice(item.price * quantity)}</span>
        {quantity > 1 ? (
          <span className="quantity">
            {quantity}x {formatPrice(item.price)}
          </span>
        ) : (
          <span></span>
        )}
      </div>
    </div>
  );
}
