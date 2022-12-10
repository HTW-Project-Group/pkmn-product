import { render, screen } from "@testing-library/react";
import * as React from "react";
import App from "./Pages/App";
import { BrowserRouter } from "react-router-dom";

test("renders cam header", () => {
  render(
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
  const linkElement = screen.getByText(/PKMN/i);
  expect(linkElement).toBeInTheDocument();
});
