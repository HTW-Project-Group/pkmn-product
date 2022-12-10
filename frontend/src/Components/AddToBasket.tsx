import * as React from "react";
import Box from "@mui/material/Box";
import InputBase from "@mui/material/InputBase";
import { useState } from "react";
import Typography from "@mui/material/Typography";
import { Button } from "@mui/material";

export default function AddToBasket({ price }: { price: number }) {
  const [amount, setAmount] = useState<number>(1);

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const input = parseInt(e.target.value);
    if (input > 0) {
      setAmount(input);
    }
  };

  return (
    <Box
      display={"flex"}
      flexWrap="wrap"
      justifyContent={"center"}
      alignItems={"center"}
      gap={"10%"}
    >
      <InputBase
        type={"number"}
        value={amount}
        onChange={handleOnChange}
        color={"primary"}
        sx={{ width: "3em", fontSize: "1.5em", justifyContent: "center" }}
      />
      <Typography fontSize={"1.5em"}>{price}</Typography>
      <Button fullWidth={true} variant={"contained"}>
        Add To Basket
      </Button>
    </Box>
  );
}
