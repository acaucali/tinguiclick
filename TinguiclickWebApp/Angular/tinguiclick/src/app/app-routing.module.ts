import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './configuracion/guards/auth.guard';
import { ConfigComponent } from './configuracion/archivosinterfaz/config.component';
import { PedidosComponent } from './pedidos/pedidos.component';
import { InicioComponent } from './inicio/inicio.component';
import { DomiciliariosComponent } from './domiciliarios/domiciliarios.component';
import { AliadosComponent } from './aliados/aliados.component';
import { TarifasComponent } from './tarifas/tarifas.component';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'usuarios', component: ConfigComponent, canActivate:[AuthGuard]},  
  {path: 'login', component: LoginComponent},
  {path: 'pedidos', component: PedidosComponent},
  {path: 'domiciliarios', component: DomiciliariosComponent},
  {path: 'aliados', component: AliadosComponent},
  {path: 'inicio', component: InicioComponent},
  {path: 'tarifa', component: TarifasComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
