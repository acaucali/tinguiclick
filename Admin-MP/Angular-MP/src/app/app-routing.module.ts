import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriasComponent } from './paginas/categorias/categorias.component';
import { DisputasComponent } from './paginas/disputas/disputas.component';
import { InicioComponent } from './paginas/inicio/inicio.component';
import { MainpageComponent } from './paginas/mainpage/mainpage.component';
import { MensajesComponent } from './paginas/mensajes/mensajes.component';
import { OrdenesComponent } from './paginas/ordenes/ordenes.component';
import { ProductosComponent } from './paginas/productos/productos.component';
import { SubcategoriasComponent } from './paginas/subcategorias/subcategorias.component';
import { TiendasComponent } from './paginas/tiendas/tiendas.component';
import { UsuariosComponent } from './paginas/usuarios/usuarios.component';
import { VentasComponent } from './paginas/ventas/ventas.component';

const routes: Routes = [
  {path: '', 
  component: MainpageComponent,
  children: [
    { path: '', component: InicioComponent},
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
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
