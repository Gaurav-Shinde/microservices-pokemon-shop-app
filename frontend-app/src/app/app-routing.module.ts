import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProductComponent } from './components/admin/add-product/add-product.component';
import { AdminComponent } from './components/admin/admin.component';
import { CartComponent } from './components/cart/cart.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { HomeComponent } from './components/home/home.component';
import { OrdersComponent } from './components/orders/orders.component';

const routes: Routes = [
  { path: "cart", component: CartComponent },
  { path: "checkout", component: CheckoutComponent },
  { path: "admin", component: AdminComponent },
  { path: "add-product", component: AddProductComponent },
  { path: "orders", component: OrdersComponent },
  { path: "", component: HomeComponent } //keep at bottom
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
