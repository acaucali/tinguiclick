import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubcategoriasComponent } from './subcategorias.component';






const routes: Routes = [
  {path: '', component: SubcategoriasComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubCategoriasRoutingModule { }
