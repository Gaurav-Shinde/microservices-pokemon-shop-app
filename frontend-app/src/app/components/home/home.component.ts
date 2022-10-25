import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: any;

  constructor(private productService: ProductService, private cartService: CartService) {

  }

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe(products => {
      this.products = products;
      // this.products.forEach((item: any) => {
      //   this.productService.getImgUrl(item.data).subscribe(data => {
      //     if (data) {
      //       item.img = data.sprites.other.dream_world.front_default;
      //     }
      //   });

      // });
    });
  }

  addToCart(product: Product) {
    console.log("adding to cart...")
    //must subscribe to service, else won't show updates in cart list
    this.cartService.addItem(product.id).subscribe();

  }

  removeFromCart() {

  }

}
