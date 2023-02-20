import * as React from "react";
import Header from "../Components/Header/Header";
import PaymentSuccessfulView from "../Components/Checkout/PaymentSuccessfulView";
import "../Css/PaymentSuccesful.css";

export default function CheckoutPage() {
  return (
    <div>
      <Header />
      <PaymentSuccessfulView />
    </div>
  );
}
