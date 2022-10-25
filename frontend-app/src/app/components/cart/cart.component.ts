import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { CartItem } from 'src/app/models/cart-item.model';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems!: CartItem[];
  totalPrice?: number;
  successAlert = false;
  proceedCheckout = false;

  constructor(private cartService: CartService, private router: Router, private orderService: OrderService) { }

  ngOnInit(): void {
    this.cartService.getAllItems().subscribe(items => {
      this.cartItems = items;

      this.totalPrice = this.cartItems.reduce((accumalator, item) => {
        accumalator = accumalator + item.product.price * item.amount;
        return accumalator;
      }, 0);
    })
  }

  confirmOrder() {

    this.orderService.saveOrder(this.cartItems).subscribe();
    this.successAlert = true
    this.proceedCheckout = false;
    this.cartService.deleteItems().subscribe();
    this.cartItems = [];
  }

  // redirectCheckout() {
  //   let navigationExtras: NavigationExtras = {
  //     queryParams: {
  //       "items": this.cartItems,
  //     }
  //   };
  //   // this.router.navigate(['checkout'], {
  //   //   state: {
  //   //     items: this.cartItems
  //   //   }
  //   // });
  //   this.router.navigate(["checkout"], navigationExtras);
  // }

}
