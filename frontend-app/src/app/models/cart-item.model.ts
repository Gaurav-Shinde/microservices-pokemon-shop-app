
import { Product } from "./product.model";

export class CartItem {
  id!: number;
  cartId!: number;
  product!: Product;
  amount!: number;
}
