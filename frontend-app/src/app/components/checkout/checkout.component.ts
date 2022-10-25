import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartItem } from 'src/app/models/cart-item.model';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  successAlert = false;
  cartItems?: CartItem[];
  constructor(private router: Router, public route: ActivatedRoute) { }

  ngOnInit(): void {
    // this.cartItems = this.router.getCurrentNavigation()!.extras!.state.items;
    // this.route.params.subscribe(params => {
    //   console.log(params)
    //   this.cartItems = params['items']
    // });
    console.log(JSON.stringify(this.route.snapshot.queryParams['items']));
    // console.log(this.cartItems);
  }

  confirmOrder() {

    this.successAlert = true;
  }

}
