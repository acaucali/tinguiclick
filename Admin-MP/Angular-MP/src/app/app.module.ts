import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ModulosModule } from './modulos/modulos.module';
import { NavBarComponent } from './modulos/nav-bar/nav-bar.component';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ModulosModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
