import * as React from "react";
import InputBase from "@mui/material/InputBase";
import { useState } from "react";
import Typography from "@mui/material/Typography";
import { Button } from "@mui/material";

export default function AddToBasket({ price }: { price: number }) {
  const [amount, setAmount] = useState<number>(1);

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const input = parseInt(e.target.value);
    if (input > 0 && input <= 100) {
      setAmount(input);
    }
  };

  return (
    <div className="addToBasket">
      <InputBase
        type={"number"}
        value={amount}
        onChange={handleOnChange}
        color={"primary"}
        sx={{ width: "3em", fontSize: "1.5em" }}
      />
      <Typography fontSize={"20px"} fontWeight={"bold"} className="detailPrice">
        {price}€
      </Typography>

      <Button fullWidth={true} variant={"contained"}>
        Add To Basket
      </Button>
    </div>
  );
}
