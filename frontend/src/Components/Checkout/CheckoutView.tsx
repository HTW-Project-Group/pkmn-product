import * as React from "react";
import { Box, Button, Divider, TextField } from "@mui/material";
import { PayPalScriptProvider, PayPalButtons } from "@paypal/react-paypal-js";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CheckoutItem from "../../Model/CheckoutItem";
import KeycloakHandler from "../../Helper/KeycloakHandler";
import { useKeycloak } from "@react-keycloak/web";
import { formatPrice } from "../../Helper/Format";

export default function CheckoutView() {
  const [credentialsApproved, setCredentialsApproved] = useState(false);
  const navigate = useNavigate();

  const { keycloak } = useKeycloak();
  const [checkoutItems, setCheckoutItems] = useState([] as CheckoutItem[]);
  const [orderSum, setOrderSum] = useState(0);

  useEffect(() => {
    const getCheckout = async (userId) => {
      const data = await fetch(
        `http://localhost:8080/v1/checkout/user/${userId}`,
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
          console.log(userResponse);

          getCheckout(userResponse.sub).then((checkoutItems) => {
            setCheckoutItems(checkoutItems);
            setOrderSum(
              checkoutItems
                .map((v) => v.price * v.quantity)
                .reduce((a, b) => a + b) + 50
            );
          });
        });
      })
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="checkout-wrapper">
      <div className="checkout-container">
        <h1>Checkout</h1>
        <h2>Order Summary</h2>
        <Divider />
        <table className="order-summary">
          <tbody>
            <tr>
              <td>Items</td>
              <td>
                {formatPrice(
                  checkoutItems.length != 0
                    ? checkoutItems
                        .map((v) => v.price * v.quantity)
                        .reduce((a, b) => a + b)
                    : 0
                )}
              </td>
            </tr>
            <tr>
              <td>Shipping</td>
              <td>50,00 €</td>
            </tr>
            <tr className="total">
              <td>Order total</td>
              <td>{formatPrice(orderSum)}</td>
            </tr>
          </tbody>
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
        <div className="billing-container">
          <PayPalScriptProvider
            options={{ "client-id": "test", currency: "EUR" }}
          >
            <PayPalButtons
              style={{ layout: "horizontal" }}
              createOrder={(data, actions) => {
                return actions.order.create({
                  purchase_units: [
                    {
                      amount: {
                        value: (Math.round(orderSum * 100) / 100)
                          .toFixed(2)
                          .toString(),
                      },
                    },
                  ],
                });
              }}
              onApprove={(data, actions) => {
                return actions.order.capture().then(() => {
                  setCredentialsApproved(true);
                });
              }}
            />
          </PayPalScriptProvider>
        </div>

        <Button
          className="buy-btn"
          variant="contained"
          disabled={!credentialsApproved}
          onClick={() => navigate("/paymentsuccessful")}
        >
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
