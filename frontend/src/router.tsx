import { createBrowserRouter } from "react-router-dom";
import { ThemeProvider } from "@mui/material";
import App from "./Pages/App";
import ProductDetailPage from "./Pages/ProductDetailPage";
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
    element: <ProductDetailPage />,
  },
  {
    path: "search/:query",
    element: <SearchResultPage />,
  },
]);
