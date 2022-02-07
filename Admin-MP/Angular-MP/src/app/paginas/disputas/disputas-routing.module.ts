import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisputasComponent } from './disputas.component';



const routes: Routes = [
  {path: '', component: DisputasComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DisputasRoutingModule { }
