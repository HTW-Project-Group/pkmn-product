import { createBrowserRouter } from "react-router-dom";
import { ThemeProvider } from "@mui/material";
import App from "./Pages/App";
import ProductDetails from "./Pages/ProductDetails";
import SearchResultPage from "./Pages/SearchResultPage";
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
    path: "details",
    element: <ProductDetails />,
  },
  {
    path: "search/:query",
    element: <SearchResultPage />,
  },
]);
