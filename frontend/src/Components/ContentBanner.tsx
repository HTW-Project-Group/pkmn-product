import * as React from "react";
import Box from "@mui/material/Box";

export default function ContentBanner() {
    return (
        <Box
            id="wallpaperdiv"
            sx={{display: "flex", justifyContent: "center", alignItems: "top", marginTop: "6%"}}
        >
            <Box
                id="logo"
                sx={{
                    zIndex: "1",
                    position: "absolute",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                }}
            >
                <img
                    src="/International_Pokémon_logo.svg (1).png"
                    width="20%"
                    height="auto"
                />
            </Box>
            <Box
                id="wallpaper"
                sx={{
                    position: "absolute",
                    zIndex: "0",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    marginTop: "4%",
                }}
            >
                <img src="/wp3228872.jpg" width="75%" height="auto"/>
            </Box>

            <div id="infobox"></div>
        </Box>
    );
}
