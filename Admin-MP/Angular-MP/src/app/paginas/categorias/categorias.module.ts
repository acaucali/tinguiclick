import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriasComponent } from './categorias.component';
import { CategoriasRoutingModule } from './categorias-routing.module';




@NgModule({
  declarations: [CategoriasComponent],
  imports: [
    CommonModule, CategoriasRoutingModule
  ]
})
export class CategoriasModule { }
