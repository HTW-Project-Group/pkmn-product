import * as React from "react";
import { CardActionArea, CardContent, CardMedia } from "@mui/material";
import Typography from "@mui/material/Typography";
import Card from "../../Model/Card";

export default function CardView(card: Card) {
  return (
    <div>
      <CardActionArea sx={{ padding: 1 }}>
        <CardMedia
          component="img"
          height="auto"
          width="auto"
          image="/img/example_pokemon.png"
        />
        <CardContent sx={{ padding: 0, paddingTop: 1 }}>
          <Typography className="card-title">{card.name}</Typography>
          <Typography className="card-price">{card.price}€</Typography>
        </CardContent>
      </CardActionArea>
    </div>
  );
}
