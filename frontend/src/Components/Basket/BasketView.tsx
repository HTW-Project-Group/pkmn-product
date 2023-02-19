import { Button, Divider } from "@mui/material";
import * as React from "react";
import BasketItemView from "./BasketItemView";
import { useNavigate } from "react-router-dom";
import { useKeycloak } from "@react-keycloak/web";
import { useEffect, useState } from "react";
import KeycloakHandler from "../../Helper/KeycloakHandler";
import Basket from "../../Model/Basket";

export default function BasketView() {
  const navigate = useNavigate();

  const { keycloak } = useKeycloak();
  const [userInfo, setUserInfo] = useState<any>({});
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
          setUserInfo(userResponse);
          console.log(userResponse);

          getBasket(userResponse.sub).then((basket) => setBasket(basket));
        });
      })
      .catch((error) => console.error(error));
  }, []);

  if (basket.items && basket.items?.length !== 0) {
    return (
      <div className="basket-container">
        <h1>Basket</h1>
        {basket.items.map((item) => (
          <BasketItemView key={item.id} item={item}></BasketItemView>
        ))}
        <div className="order-sum-container">
          <span className="order-sum-label">Sum</span>
          <Divider />
          <span className="order-sum-value">
            {basket.items
              .map((v) => v.price * v.quantity)
              .reduce((a, b) => a + b)}{" "}
            €
          </span>
          <Button variant="contained" onClick={() => navigate("/checkout")}>
            Checkout
          </Button>
        </div>
      </div>
    );
  } else {
    return (
      <div className="basket-container">
        <h1>Basket</h1>
        <p>Your basket is empty</p>
      </div>
    );
  }
}
