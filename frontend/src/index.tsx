import * as React from "react";
import * as ReactDOM from "react-dom/client";
import "./Css/index.css";

import zreportWebVitals from "./zreportWebVitals";
import { RouterProvider } from "react-router-dom";
import { router } from "./router";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import Keycloak from "keycloak-js";
import KeycloakHandler from "./Helper/KeycloakHandler";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);

const keycloak = new Keycloak({
  url: "http://localhost:8180",
  realm: "pokemon",
  clientId: "pokemonClient",
});

root.render(
  <ReactKeycloakProvider
    authClient={keycloak}
    initOptions={{
      onLoad: "check-sso",
    }}
    onTokens={() => KeycloakHandler.instance().finishKeycloakLoading()}
  >
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>
  </ReactKeycloakProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: zreportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
zreportWebVitals();
