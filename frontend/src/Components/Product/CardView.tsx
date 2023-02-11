import * as React from "react";
import { CardActionArea, CardContent, CardMedia } from "@mui/material";
import Typography from "@mui/material/Typography";
import Card from "../../Model/Card";
import {formatPrice} from "../../Helper/Format";

export default function CardView(card: Card) {
  return (
    <div>
      <CardActionArea sx={{ padding: 1 }}>
        <CardMedia
          component="img"
          height="auto"
          width="auto"
          image={
            "https://images.pokemontcg.io/" +
            card.pokemonId.replaceAll("-", "/") +
            ".png"
          }
        />
        <CardContent sx={{ padding: 0, paddingTop: 1 }}>
          <Typography className="card-title">{card.name}</Typography>
          <Typography className="card-price">
            {formatPrice(card.price)}
          </Typography>
        </CardContent>
      </CardActionArea>
    </div>
  );
}
