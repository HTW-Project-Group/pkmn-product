import { render, screen } from "@testing-library/react";
import * as React from "react";
import App from "./App";

test("renders cam header", () => {
  render(<App />);
  const linkElement = screen.getByText(/PKMN/i);
  expect(linkElement).toBeInTheDocument();
});
