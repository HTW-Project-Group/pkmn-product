import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import SearchBar from "./SearchBar";
import NavActions from "./NavActions";

export default function DrawerAppBar() {
  return (
    <Box id="BlueBar" sx={{ display: "flex" }}>
      <AppBar component="nav">
        <Toolbar sx={{ justifyContent: "space-between" }}>
          <Typography
            variant="h6"
            component="div"
            sx={{ display: { xs: "none", sm: "block" } }}
          >
            PKMN
          </Typography>
          <SearchBar></SearchBar>
          <NavActions></NavActions>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
