import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ModulosModule } from '../modulos/modulos.module';



@NgModule({
  declarations: [
    MainpageComponent
  ],
  imports: [
    CommonModule, 
    ModulosModule
  ]
})
export class PaginasModule { }
