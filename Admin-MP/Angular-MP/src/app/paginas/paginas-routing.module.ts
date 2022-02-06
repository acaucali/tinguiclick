import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriasComponent } from './categorias/categorias.component';
import { DisputasComponent } from './disputas/disputas.component';

import { MainpageComponent } from './mainpage/mainpage.component';
import { MensajesComponent } from './mensajes/mensajes.component';
import { OrdenesComponent } from './ordenes/ordenes.component';
import { ProductosComponent } from './productos/productos.component';
import { SubcategoriasComponent } from './subcategorias/subcategorias.component';
import { TiendasComponent } from './tiendas/tiendas.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { VentasComponent } from './ventas/ventas.component';

const routes: Routes = [
  {path: '', 
  component: MainpageComponent,
  children: [
    { path: '', loadChildren: () => import('./inicio/inicio.module').then(m =>m.InicioModule)},
    { path: 'usuarios', component: UsuariosComponent},
    { path: 'categorias', component: CategoriasComponent},
    { path: 'subcategorias', component: SubcategoriasComponent},
    { path: 'tiendas', component: TiendasComponent},
    { path: 'productos', component: ProductosComponent},
    { path: 'ordenes', component: OrdenesComponent},
    { path: 'ventas', component: VentasComponent},
    { path: 'mensajes', component: MensajesComponent},
    { path: 'disputas', component: DisputasComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaginasRoutingModule { }
