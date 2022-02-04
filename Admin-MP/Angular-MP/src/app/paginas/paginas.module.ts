import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ModulosModule } from '../modulos/modulos.module';
import { InicioComponent } from './inicio/inicio.component';
import { AppRoutingModule } from '../app-routing.module';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { CategoriasComponent } from './categorias/categorias.component';
import { SubcategoriasComponent } from './subcategorias/subcategorias.component';
import { TiendasComponent } from './tiendas/tiendas.component';
import { ProductosComponent } from './productos/productos.component';
import { OrdenesComponent } from './ordenes/ordenes.component';
import { VentasComponent } from './ventas/ventas.component';
import { MensajesComponent } from './mensajes/mensajes.component';
import { DisputasComponent } from './disputas/disputas.component';




@NgModule({
  declarations: [
    MainpageComponent,
    InicioComponent,
    UsuariosComponent,
    CategoriasComponent,
    SubcategoriasComponent,
    TiendasComponent,
    ProductosComponent,
    OrdenesComponent,
    VentasComponent,
    MensajesComponent,
    DisputasComponent
  ],
  imports: [
    CommonModule, 
    ModulosModule,
    AppRoutingModule
  ]
})
export class PaginasModule { }
