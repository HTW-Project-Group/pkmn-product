import * as React from "react";
import Box from "@mui/material/Box";
import { createTheme, responsiveFontSizes} from "@mui/material/styles";
import AddToBasket from "./AddToBasket";
import Card from "../Model/Card";
import { Collapse} from "@mui/material";

let theme = createTheme();
theme = responsiveFontSizes(theme);


export default function ProductDetails({
  id,
  name,
  price,
  description,
  condition,
  pokemonId,
}: Card) {
  const [open, setOpen] = React.useState(false);

  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <Box className="productDetail">
      <Box className="detailImageContainer">
        <img
          className="detailImage"
          src={
            "https://images.pokemontcg.io/" +
            pokemonId.replaceAll("-", "/") +
            "_hires.png"
          }
        />
      </Box>
      <Box className="details">
        <Box className="description">
          <h1 className="detailName">{name}</h1>
          <h3>Description</h3>
          <p>{description}</p>
        </Box>
        <Box>
          <h2 onClick={handleClick}>Details</h2>
          <Collapse in={open} timeout="auto" unmountOnExit>
            MEHR TEXT ILfsdjkfhkasdhfaklsjhfasklhjf
          </Collapse>
        </Box>
        <Box className={"addToBasketContainer"}>
          <AddToBasket price={price} />
        </Box>
      </Box>
    </Box>
  );
}
