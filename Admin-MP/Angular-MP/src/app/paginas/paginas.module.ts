import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ModulosModule } from '../modulos/modulos.module';

import { AppRoutingModule } from '../app-routing.module';
import { Pagina404Component } from './pagina404/pagina404.component';




@NgModule({
  declarations: [
    MainpageComponent,
    Pagina404Component,
    
  ],
  imports: [
    CommonModule, 
    ModulosModule,
    AppRoutingModule
  ]
})
export class PaginasModule { }
