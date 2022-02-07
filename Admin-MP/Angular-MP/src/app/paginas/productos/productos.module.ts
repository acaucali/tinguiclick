import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductosComponent } from './productos.component';
import { ProductosRoutingModule } from './productos-routing.module';







@NgModule({
  declarations: [ProductosComponent],
  imports: [
    CommonModule, ProductosRoutingModule
  ]
})
export class ProductosModule { }
