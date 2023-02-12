import { Box, Button, Divider, TextField } from "@mui/material";
import * as React from "react";

export default function CheckoutView() {
  return (
    <div className="checkout-wrapper">
      <div className="checkout-container">
        <h1>Checkout</h1>
        <h2>Order Summary</h2>
        <Divider />
        <table className="order-summary">
          <tr>
            <td>Items</td>
            <td>1424,99 €</td>
          </tr>
          <tr>
            <td>Shipping</td>
            <td>50,00 €</td>
          </tr>
          <tr className="total">
            <td>Order total</td>
            <td>1474,99 €</td>
          </tr>
        </table>

        <h2>Adress</h2>
        <Box className="adress-form" component="form">
          <div className="form-group g-1-1">
            <TextField required label="Name" variant="outlined" />
            <TextField required label="First Name" variant="outlined" />
          </div>
          <TextField required label="Email" variant="outlined" />
          <TextField label="Tel." variant="outlined" />
          <div className="form-group g-3-1">
            <TextField required label="Street" variant="outlined" />
            <TextField
              className="small"
              required
              label="Nr."
              variant="outlined"
            />
          </div>
          <div className="form-group g-3-1">
            <TextField required label="City" variant="outlined" />
            <TextField
              className="small"
              required
              label="Zip Code"
              variant="outlined"
            />
          </div>
        </Box>

        <h2>Billing</h2>
        <div className="billing-container"></div>

        <Button className="buy-btn" variant="contained">
          Buy
        </Button>
      </div>
      <img
        className="side-cover"
        src="/img/pikachu_side_cropped_cleared.png"
        alt="Checkout Cover Pikachu"
      />
    </div>
  );
}
