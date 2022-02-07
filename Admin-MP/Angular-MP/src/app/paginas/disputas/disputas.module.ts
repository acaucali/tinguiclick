import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DisputasRoutingModule } from './disputas-routing.module';
import { DisputasComponent } from './disputas.component';





@NgModule({
  declarations: [DisputasComponent],
  imports: [
    CommonModule, DisputasRoutingModule
  ]
})
export class DisputasModule { }
