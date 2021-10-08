import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { NgxPaginationModule } from 'ngx-pagination';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { DetalleComponent } from './configuracion/detalle/detalle.component';

import { MenuComponent } from './menu/menu.component';
import { TokenInterceptor } from './configuracion/interceptors/token.interceptor';
import { AuthInterceptor } from './configuracion/interceptors/auth.interceptor';
import { AgmCoreModule } from '@agm/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatTreeModule, MatIconModule, MatButtonModule, MatNativeDateModule, MatSelectModule } from '@angular/material';
import { PedidosComponent } from './pedidos/pedidos.component';
import { UsuariosService } from './configuracion/usuarios/usuario.service';
import { ConfigComponent } from './configuracion/archivosinterfaz/config.component';
import { InicioComponent } from './inicio/inicio.component';
import { PanelComponent } from './panel/panel.component';
import { TipoIdentificacionService } from './configuracion/parametros/tablas/tipoIdentificacionService';
import { DomiciliariosComponent } from './domiciliarios/domiciliarios.component';
import { DetalleDomicilioComponent } from './domiciliarios/detalle-domicilio/detalle-domicilio.component';
import { DomiciliariosService } from './domiciliarios/model/domiciliarios.service';
import { AliadosComponent } from './aliados/aliados.component';
import { DetalleAliadoComponent } from './aliados/detalle-aliado/detalle-aliado.component';
import { AliadosService } from './aliados/model/aliados.service';
import { TarifasComponent } from './tarifas/tarifas.component';
import { DetalleTarifaComponent } from './tarifas/detalle-tarifa/detalle-tarifa.component';
import { TarifaService } from './tarifas/model/tarifa.service';
import { DetallePedidoComponent } from './pedidos/detalle-pedido/detalle-pedido.component';
import { PedidosService } from './pedidos/model/pedidos.service';
import { DetalleFacturaComponent } from './pedidos/detalle-factura/detalle-factura.component';
import { DetalleDataComponent } from './pedidos/detalle/detalle.component';
import { FacturaComponent } from './pedidos/factura/factura.component';
import { ExcelService } from './pedidos/util/excelservice';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ConfigComponent,
    DetalleComponent,
    MenuComponent,
    PedidosComponent,
    InicioComponent,
    PanelComponent,
    DomiciliariosComponent,
    DetalleDomicilioComponent,
    AliadosComponent,
    DetalleAliadoComponent,
    TarifasComponent,
    DetalleTarifaComponent,
    DetallePedidoComponent,
    DetalleFacturaComponent,
    DetalleDataComponent,
    FacturaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    MatTableModule, MatPaginatorModule,
    NgxPaginationModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTreeModule, MatIconModule, MatButtonModule,
    MatSelectModule, 
    MDBBootstrapModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: ''
    })
  ],
  providers: [UsuariosService, TipoIdentificacionService, DomiciliariosService, AliadosService, TarifaService,
    PedidosService, ExcelService,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
