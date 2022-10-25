import { OrderItem } from "./order-item.model";

export class Order {
  id!: number;
  date!: string;
  total!: number;
  items!: OrderItem[];

}
