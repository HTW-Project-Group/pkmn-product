export default interface BasketItem {
  id: string;
  productId: string;
  name: string;
  description: string;
  img: string;
  quantity: number;
  price: number;
}

export default interface Basket {
  userId: string;
  items: BasketItem[];
}
