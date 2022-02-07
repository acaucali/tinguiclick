import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SubcategoriasComponent } from './subcategorias.component';
import { SubCategoriasRoutingModule } from './subcategorias-routing.module';







@NgModule({
  declarations: [SubcategoriasComponent],
  imports: [
    CommonModule, SubCategoriasRoutingModule
  ]
})
export class SubCategoriasModule { }
