import * as React from "react";
import {
  CardActionArea,
  CardContent,
  CardMedia,
  createTheme,
  ThemeProvider,
} from "@mui/material";
import Typography from "@mui/material/Typography";

type cardprops = {
  name: string;
  image: string;
  id: string;
  price: number;
};

const theme1 = createTheme();

theme1.typography.body2 = {
  fontSize: "0.5rem",
  "@media (min-height: 70px )": {
    fontSize: "10px",
  },
};
const theme2 = createTheme();

theme2.typography.h5 = {
  fontSize: "1.5rem",
  "@media (min-height: 70px )": {
    fontSize: "15px",
  },
};
const theme3 = createTheme();

theme3.typography.h6 = {
  fontSize: "2rem",
  "@media (min-height: 70px )": {
    fontSize: "13px",
  },
};
export default function Cards({ name, image, id, price }: cardprops) {
  return (
    <div /* style={{overflow:"hidden"}}*/>
      <CardActionArea
        sx={{
          borderRadius: 0,
          transition: "0.2s",
          "&:hover": {
            transform: "scale(1.03)",
          },
          padding: 1,
          zIndex: "0",
          boxShadow: "0 8px 16px 0 #BDC9D7",
        }}
      >
        <CardMedia component="img" height="auto" width="auto" image={image} />
        <CardContent sx={{ padding: 0, paddingTop: 1 }}>
          <ThemeProvider theme={theme2}>
            <Typography
              height="auto"
              width="auto"
              gutterBottom
              variant="h5"
              component="div"
            >
              {name}
            </Typography>
          </ThemeProvider>
          <ThemeProvider theme={theme3}>
            <Typography
              height="auto"
              width="auto"
              gutterBottom
              variant="h6"
              component="div"
            >
              {price}
            </Typography>
          </ThemeProvider>
          <ThemeProvider theme={theme1}>
            <Typography
              height="auto"
              width="auto"
              variant="body2"
              color="text.secondary"
            >
              Hier steht die Beschreibung
            </Typography>
          </ThemeProvider>
        </CardContent>
      </CardActionArea>
    </div>
  );
}
