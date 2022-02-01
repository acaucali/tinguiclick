import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ModulosModule } from '../modulos/modulos.module';
import { InicioComponent } from './inicio/inicio.component';




@NgModule({
  declarations: [
    MainpageComponent,
    InicioComponent
  ],
  imports: [
    CommonModule, 
    ModulosModule
  ]
})
export class PaginasModule { }
