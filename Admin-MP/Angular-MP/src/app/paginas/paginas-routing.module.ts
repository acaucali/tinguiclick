import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainpageComponent } from './mainpage/mainpage.component';
import { Pagina404Component } from './pagina404/pagina404.component';


const routes: Routes = [
  {path: 'login', loadChildren: () => import('./login/login.module').then(m =>m.LoginModule)},
  {path: '', 
  component: MainpageComponent,
  children: [
    { path: '', loadChildren: () => import('./inicio/inicio.module').then(m =>m.InicioModule)},
    { path: 'usuarios', loadChildren: () => import('./usuarios/usuarios.module').then(m =>m.UsuariosModule)},
    { path: 'categorias', loadChildren: () => import('./categorias/categorias.module').then(m =>m.CategoriasModule)},
    { path: 'subcategorias', loadChildren: () => import('./subcategorias/subcategorias.module').then(m =>m.SubCategoriasModule)},
    { path: 'tiendas', loadChildren: () => import('./tiendas/tiendas.module').then(m =>m.TiendasModule)},
    { path: 'productos', loadChildren: () => import('./productos/productos.module').then(m =>m.ProductosModule)},
    { path: 'ordenes', loadChildren: () => import('./ordenes/ordenes.module').then(m =>m.OrdenesModule)},
    { path: 'ventas', loadChildren: () => import('./ventas/ventas.module').then(m =>m.VentasModule)},
    { path: 'mensajes', loadChildren: () => import('./mensajes/mensajes.module').then(m =>m.MensajesModule)},
    { path: 'disputas', loadChildren: () => import('./disputas/disputas.module').then(m =>m.DisputasModule)},
    { path: '**', component: Pagina404Component},
  ]}
];
 
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaginasRoutingModule { }
