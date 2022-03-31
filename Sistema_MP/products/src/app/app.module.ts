import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './modules/header/header.component';
import { HeaderMobileComponent } from './modules/header-mobile/header-mobile.component';
import { NewletterComponent } from './modules/newletter/newletter.component';
import { FooterComponent } from './modules/footer/footer.component';
import { HomeComponent } from './page/home/home.component';
import { ProductsComponent } from './page/products/products.component';
import { ProductComponent } from './page/product/product.component';
import { SearchComponent } from './page/search/search.component';
import { Error404Component } from './page/error404/error404.component';
import { HomeFeaturesComponent } from './page/home/home-features/home-features.component';
import { HomeHotTodayComponent } from './page/home/home-hot-today/home-hot-today.component';
import { HomeTopCategoriesComponent } from './page/home/home-top-categories/home-top-categories.component';
import { HomeShowcaseComponent } from './page/home/home-showcase/home-showcase.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HeaderMobileComponent,
    NewletterComponent,
    FooterComponent,
    HomeComponent,
    ProductsComponent,
    ProductComponent,
    SearchComponent,
    Error404Component,
    HomeFeaturesComponent,
    HomeHotTodayComponent,
    HomeTopCategoriesComponent,
    HomeShowcaseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
