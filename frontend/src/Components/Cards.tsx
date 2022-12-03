import * as React from "react";
import { CardActionArea, CardContent, CardMedia } from "@mui/material";
import Typography from "@mui/material/Typography";

type cardprops = {
    name: string;
    image: string;
    id: string;
    price: number;
};

export default function Cards({ name, image, id, price }: cardprops) {
  return (
    <CardActionArea
      sx={{
        borderRadius: 0,
        transition: "0.2s",
        "&:hover": {
          transform: "scale(1.03)",
        },
        padding: 2,
        zIndex: "0",
        boxShadow: "0 8px 16px 0 #BDC9D7",
      }}
    >
      <CardMedia component="img" height="auto" width="auto" image={image}/>
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {name}
        </Typography>
        <Typography gutterBottom variant="h6" component="div">
          {price}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Hier steht die Beschreibung
        </Typography>
      </CardContent>
    </CardActionArea>
  );
}
