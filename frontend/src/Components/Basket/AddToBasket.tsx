import * as React from "react";
import { useEffect, useState } from "react";
import {
  AlertProps,
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  Snackbar,
} from "@mui/material";
import MuiAlert from "@mui/material/Alert";
import { formatPrice } from "../../Helper/Format";
import Card from "../../Model/Card";
import ProductDto from "../../Model/ProductDto";
import { useKeycloak } from "@react-keycloak/web";
import KeycloakHandler from "../../Helper/KeycloakHandler";

const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(
  props,
  ref
) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export default function AddToBasket({ product }: { product: Card }) {
  const { keycloak } = useKeycloak();
  const [userInfo, setUserInfo] = useState<any>({});

  useEffect(() => {
    KeycloakHandler.instance()
      .onKeycloakLoaded()
      .then(() => {
        keycloak.loadUserInfo().then((userResponse) => {
          setUserInfo(userResponse);
          console.log(userResponse);
        });
      })
      .catch((error) => console.error(error));
  }, []);

  const [quantity, setQuantity] = useState(1);

  const quantityChange = (e) => {
    setQuantity(e.target.value);
  };

  const [showSnackbar, setShowSnackbar] = useState(false);

  const handleSnackbarClose = (
    event?: React.SyntheticEvent | Event,
    reason?: string
  ) => {
    if (reason === "clickaway") {
      return;
    }
    setShowSnackbar(false);
  };

  const addToBasketRequest = () => {
    if (userInfo) {
      return fetch(`http://localhost:8080/v1/product/queue/basket/add`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userId: userInfo.sub,
          pokemonId: product.pokemonId,
          name: product.name,
          description: product.description,
          quantity,
          price: product.price,
        } as ProductDto),
      }).then(() => {
        setShowSnackbar(true);
      });
    }
  };

  return (
    <div>
      <span className="detail-price">{formatPrice(product.price)}</span>
      <div className="add-basket-group">
        <Button
          variant="contained"
          sx={{ height: 40 }}
          onClick={addToBasketRequest}
        >
          Add To Basket
        </Button>
        <FormControl sx={{ m: 1, minWidth: 120 }} size="small">
          <InputLabel id="quantity-select">Quantity</InputLabel>
          <Select
            labelId="quantity-select"
            id="quantity-select"
            value={quantity}
            label="Quantity"
            onChange={quantityChange}
          >
            {Array(10)
              .fill(0)
              .map((_, i) => (
                <MenuItem key={`sel-${i + 1}`} value={i + 1}>
                  {i + 1}
                </MenuItem>
              ))}
          </Select>
        </FormControl>
      </div>
      <Snackbar
        open={showSnackbar}
        autoHideDuration={6000}
        onClose={handleSnackbarClose}
      >
        <Alert
          onClose={handleSnackbarClose}
          severity="info"
          sx={{ width: "100%" }}
        >
          {product.name} was added to basket
        </Alert>
      </Snackbar>
    </div>
  );
}
