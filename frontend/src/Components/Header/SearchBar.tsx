import * as React from "react";
import { styled, alpha } from "@mui/material/styles";
import InputBase from "@mui/material/InputBase";
import SearchIcon from "@mui/icons-material/Search";
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import FilterListIcon from "@mui/icons-material/FilterList";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Typography from "@mui/material/Typography";
import Tooltip from "@mui/material/Tooltip";

const categories = ["Fire", "Water", "Grass", "Electric"];

const Search = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  "&:hover": {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginLeft: 0,
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(1),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  "& .MuiInputBase-input": {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("sm")]: {
      width: "12ch",
      "&:focus": {
        width: "20ch",
      },
    },
  },
}));

export default function SearchBar() {
  const [
    anchorElCategory,
    setAnchorElCategory,
  ] = React.useState<null | HTMLElement>(null);

  const handleOpenCategoryMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElCategory(event.currentTarget);
  };
  const handleCloseUserMenu = () => {
    setAnchorElCategory(null);
  };
  return (
    <Box className="search-bar">
      <Search className="search-field">
        <SearchIconWrapper>
          <SearchIcon />
        </SearchIconWrapper>
        <StyledInputBase
          sx={{ width: "100%" }}
          placeholder="Search…"
          inputProps={{ "aria-label": "search" }}
        />
      </Search>
      <Box className="search-filter">
        <Tooltip title={"Filter"}>
          <IconButton onClick={handleOpenCategoryMenu} color="inherit">
            <FilterListIcon />
          </IconButton>
        </Tooltip>
        <Menu
          sx={{ mt: "45px" }}
          id="menu-appbar"
          anchorEl={anchorElCategory}
          anchorOrigin={{
            vertical: "top",
            horizontal: "right",
          }}
          keepMounted
          transformOrigin={{
            vertical: "top",
            horizontal: "right",
          }}
          open={Boolean(anchorElCategory)}
          onClose={handleCloseUserMenu}
        >
          {categories.map((category) => (
            <MenuItem key={category} onClick={handleCloseUserMenu}>
              <Typography textAlign="center">{category}</Typography>
            </MenuItem>
          ))}
        </Menu>
      </Box>
    </Box>
  );
}
