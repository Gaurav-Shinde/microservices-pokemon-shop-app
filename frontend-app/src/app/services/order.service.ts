import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CartItem } from '../models/cart-item.model';
import { Order } from '../models/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  url = "http://localhost:9191/orders"

  constructor(private http: HttpClient) { }

  public getAllOrders(): Observable<any> {
    return this.http.get<Order[]>(this.url);
  }

  public saveOrder(carItems: CartItem[]) {
    return this.http.post<Order>(this.url, carItems);
  }
}
