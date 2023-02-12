import { createBrowserRouter } from "react-router-dom";
import { ThemeProvider } from "@mui/material";
import App from "./Pages/App";
import ProductDetailPage from "./Pages/ProductDetailPage";
import SearchResultPage from "./Pages/SearchResultPage";
import BasketPage from "./Pages/BasketPage";
import CheckoutPage from "./Pages/CheckoutPage";
import * as React from "react";
import { theme } from "./theme";

export const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <ThemeProvider theme={theme}>
        <App />
      </ThemeProvider>
    ),
  },
  {
    path: "details/:id",
    element: <ProductDetailPage />,
  },
  {
    path: "search/:query",
    element: <SearchResultPage />,
  },
  {
    path: "basket",
    element: <BasketPage />,
  },
  {
    path: "checkout",
    element: <CheckoutPage />,
  },
]);
