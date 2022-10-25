import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  // imageUploadCheck = false;
  formObj = new Product();
  successAlert = false;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
  }

  async submit() {
    console.log(this.formObj)
    await this.productService.addProduct(this.formObj).subscribe();

    this.successAlert = true;
    //reset
    this.formObj = new Product();
    console.log(this.formObj);
  }

}
