import * as React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import {createTheme, responsiveFontSizes} from "@mui/material/styles";
import {ThemeProvider} from "@mui/material";
import AddToBasket from "./AddToBasket";
import Card from "../Model/Card";

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
    return (
        <Box
            display={"flex"}
            flexWrap={"wrap"}
            gap={"4%"}
            maxWidth={"75%"}
            maxHeight={"100%"}
            alignContent={"center"}
            justifyContent={"center"}
        >
            <Box maxHeight={"100%"} maxWidth={"46%"}>
                <img
                    src={"https://images.pokemontcg.io/" +
                        pokemonId.replaceAll("-", "/") +
                        "_hires.png"}

                    style={{
                        maxWidth: "100%",
                        maxHeight: "100%",
                    }}
                />
            </Box>
            <Box maxHeight={"100%"} maxWidth={"46%"}>
                <Box>
                    <ThemeProvider theme={theme}>
                        <Typography fontSize={"2vw"}>{name}</Typography>
                    </ThemeProvider>
                    <ThemeProvider theme={theme}>
                        <Typography fontSize={"1.1vw"} marginTop={"5%"}>
                            Description
                        </Typography>
                    </ThemeProvider>
                    <ThemeProvider theme={theme}>
                        <Typography fontSize={"0.91vw"} marginTop={"1%"}>
                            {description}
                        </Typography>
                    </ThemeProvider>
                </Box>
                <Box
                    marginLeft={"auto"}
                    maxWidth={"50%"}
                    maxHeight={"15%"}
                    marginTop={"9%"}
                >
                    <AddToBasket price={price}/>
                </Box>
            </Box>
        </Box>
    );
}
