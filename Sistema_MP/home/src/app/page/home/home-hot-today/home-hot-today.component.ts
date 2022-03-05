import { Component, OnInit } from '@angular/core';
import { Path } from '../../../config';
import { OwlCarouselConfig, CarouselNavigation } from '../../../functions';

import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-home-hot-today',
  templateUrl: './home-hot-today.component.html',
  styleUrls: ['./home-hot-today.component.css']
})
export class HomeHotTodayComponent implements OnInit {

  path:String = Path.url;
  indexes:Array<any> = [];
  render:Boolean = true;
  constructor(private productsService : ProductsService) { }

  ngOnInit(): void {
    let getProducts = [];
    let hoy = new Date();
    let fechaOferta = null;
    /*=====================================
    Tomamos la data de los productos
    ======================================*/

    this.productsService.getData()
        .subscribe( resp =>{
          console.log("resp", resp);

          /*=====================================
          Recorremos cada producto para separar las ofertas y el stock
          ======================================*/
          let i;

          for(i in resp){
            getProducts.push({
              "offer": JSON.parse(resp[i].offer),
              "stock": resp[i].stock
            })
          }


          /*=====================================
          Recorremos cada oferta y stock para clasificar las ofertas actuales y los
          productos que si tengan stock
          ======================================*/

          for(i in getProducts){
            fechaOferta = new Date(
              parseInt(getProducts[i]["offer"][2].split("-")[0]),
              parseInt(getProducts[i]["offer"][2].split("-")[1])-1,
              parseInt(getProducts[i]["offer"][2].split("-")[2])
            );

            if(hoy < fechaOferta && getProducts[i]["stock"] > 0){
              this.indexes.push(i);
              console.log(this.indexes);
            }
          }
        })
  }
  /*=====================================
          Función que nos avisa cuando finaliza el renderizado de Angular
          ======================================*/
  callback(){
    if(this.render){
      this.render = false;
      OwlCarouselConfig.fnc();
      CarouselNavigation.fnc();
    }
  }

}
