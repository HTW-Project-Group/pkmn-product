import * as React from "react";
import Box from "@mui/material/Box";

export default function ContentBanner() {
  return (
    <Box className="content-banner">
      <img
        className="pokemon-logo"
        src="/img/pokemon_logo.png"
        alt="Pokemon Logo"
      />
      <img
        className="pokemon-overview"
        src="/img/pokemon_overview.jpg"
        alt="Pokemon Overview"
      />
    </Box>
  );
}
