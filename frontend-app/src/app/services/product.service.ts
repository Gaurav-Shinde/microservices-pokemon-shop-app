import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }
  private url = "http://localhost:9191/products";
  public getAllProducts(): Observable<any> {
    return this.http.get<Product[]>(this.url);
  }

  public addProduct(product: Product): Observable<any> {
    return this.http.post<any>(this.url, product);
  }
}
