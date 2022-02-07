import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ModulosModule } from '../modulos/modulos.module';

import { AppRoutingModule } from '../app-routing.module';




@NgModule({
  declarations: [
    MainpageComponent,
    
  ],
  imports: [
    CommonModule, 
    ModulosModule,
    AppRoutingModule
  ]
})
export class PaginasModule { }
