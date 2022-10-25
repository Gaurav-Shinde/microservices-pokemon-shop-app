import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CartItem } from '../models/cart-item.model';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private url = "http://localhost:9191/cart-items";

  constructor(private http: HttpClient) { }

  //get all items
  //@Return: list of cartitems
  public getAllItems(): Observable<any> {
    return this.http.get(this.url);
  }

  //add card to cart
  //@Return: cart
  public addItem(productId: number): Observable<any> {
    return this.http.get(this.url + "/" + productId);
  }

  public deleteItems(): Observable<any> {
    return this.http.delete(this.url);
  }
}
