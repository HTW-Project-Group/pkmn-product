import * as React from "react";
import { Box, IconButton, Typography, Badge } from "@mui/material";
import Menu from "@mui/material/Menu";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import { AccountCircle } from "@mui/icons-material";
import LocalGroceryStoreIcon from "@mui/icons-material/LocalGroceryStore";
import { useNavigate } from "react-router-dom";
import { useKeycloak } from "@react-keycloak/web";
import { useEffect, useState } from "react";
import KeycloakHandler from "../../Helper/KeycloakHandler";
import Basket from "../../Model/Basket";

export default function NavActions() {
  const navigate = useNavigate();

  const { keycloak } = useKeycloak();
  const [basket, setBasket] = useState({ items: [] } as Basket);

  useEffect(() => {
    const getBasket = async (userId) => {
      const data = await fetch(
        `http://localhost:8081/v1/basket/user/${userId}`,
        {
          method: "GET",
        }
      );
      return data.json();
    };

    KeycloakHandler.instance()
      .onKeycloakLoaded()
      .then(() => {
        keycloak.loadUserInfo().then((userResponse: any) => {
          getBasket(userResponse.sub).then((basket) => setBasket(basket));
        });
      })
      .catch((error) => console.error(error));
  }, []);

  const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(
    null
  );

  const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElUser(event.currentTarget);
  };
  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };
  const handleLoginLogout = (authenticated) => {
    if (!authenticated) {
      window.location.replace(
        "http://localhost:8180/realms/pokemon/protocol/openid-connect/auth?response_type=code&client_id=pokemonClient&redirect_uri=" +
          window.location.href
      );
    } else {
      window.location.replace(
        `http://localhost:8180/realms/pokemon/protocol/openid-connect/logout?redirect_uri=` +
          window.location.href
      );
    }
  };

  return (
    <Box sx={{ display: "flex" }}>
      <Box>
        <Tooltip title="Basket">
          <IconButton
            color="inherit"
            aria-label="add to shopping cart"
            onClick={() => navigate("/basket")}
          >
            {basket.items && basket.items?.length !== 0 ? (
              <Badge badgeContent={basket.items?.length} color="error">
                <LocalGroceryStoreIcon />
              </Badge>
            ) : (
              <LocalGroceryStoreIcon />
            )}
          </IconButton>
        </Tooltip>
      </Box>
      <Box>
        <Tooltip title="Actions">
          <IconButton onClick={handleOpenUserMenu} color="inherit">
            <AccountCircle />
          </IconButton>
        </Tooltip>
        <Menu
          sx={{ mt: "45px" }}
          id="menu-appbar"
          anchorEl={anchorElUser}
          anchorOrigin={{
            vertical: "top",
            horizontal: "right",
          }}
          keepMounted
          transformOrigin={{
            vertical: "top",
            horizontal: "right",
          }}
          open={Boolean(anchorElUser)}
          onClose={handleCloseUserMenu}
        >
          <MenuItem onClick={() => handleLoginLogout(keycloak.authenticated)}>
            <Typography textAlign="center">
              {keycloak.authenticated ? "Logout" : "Login"}
            </Typography>
          </MenuItem>
        </Menu>
      </Box>
    </Box>
  );
}
