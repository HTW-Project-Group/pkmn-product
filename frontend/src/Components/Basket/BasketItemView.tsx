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
import { useEffect, useState } from "react";
import Basket from "../../Model/Basket";

export default function BasketItemView({
  item,
  onUpdate,
}: {
  item: BasketItem;
  onUpdate(basket: Basket): void;
}) {
  const [quantity, setQuantity] = useState(item.quantity);

  const quantityChange = (e) => {
    const newQuantity = e.target.value;
    setQuantity(newQuantity);

    const updateBasketItem = async () => {
      const data = await fetch(`http://localhost:8081/v1/basket/update`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: item.id,
          userId: item.userId,
          pokemonId: item.pokemonId,
          name: item.name,
          description: item.description,
          quantity: newQuantity,
          price: item.price,
        } as BasketItem),
      });
      return data.json();
    };

    updateBasketItem().then((basket) => onUpdate(basket));
  };

  const removeBasketItem = () => {
    const remove = async () => {
      const data = await fetch(
        `http://localhost:8081/v1/basket/delete/${item.id}`,
        {
          method: "DELETE",
        }
      );
      return data.json();
    };
    remove().then((basket) => onUpdate(basket));
  };

  return (
    <div className="basket-item">
      <div className="basket-item-img">
        <img
          src={
            "https://images.pokemontcg.io/" +
            item.pokemonId.replaceAll("-", "/") +
            ".png"
          }
          alt="Pokemon Image"
        />
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
            onClick={removeBasketItem}
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
