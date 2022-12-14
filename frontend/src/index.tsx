import * as React from "react";
import * as ReactDOM from "react-dom/client";
import "./index.css";

import reportWebVitals from "./reportWebVitals";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import ProductDetails from "./Pages/ProductDetails";
import App from "./Pages/App";
import { createTheme, ThemeProvider } from "@mui/material";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
const theme = createTheme({
  palette: {
    primary: {
      main: "#2979C1",
    },
    secondary: {
      main: "#FFCB05",
    },
  },
});

const router = createBrowserRouter([
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
]);

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
