import { Component, OnInit } from '@angular/core';
import { Path } from '../../config';
import { CategoriesService } from 'src/app/services/categories.service';
import { SubCategoriesService } from 'src/app/services/sub-categories.service';

declare var jQuery:any;
declare var $:any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  path:String = Path.url;
  categories:any[];
  arrayTitleList:Array<any> = [];
  render:Boolean = true;
  constructor(private categoriesService: CategoriesService,
    private subCategoriesService:SubCategoriesService) { }

  ngOnInit(): void {

    /*=====================================
    Tomamos la data de las categorías
    ======================================*/

    this.categoriesService.getData()
    .subscribe(resp =>{
      this.categories = resp as any[];

      /*=====================================
      Recorremos la colección de categorías para tomar la lista de títulos
      ======================================*/
      let i;
      for(i in resp){

        /*=====================================
        Separamos la lista de títulos en índices de un array
        ======================================*/

        this.arrayTitleList.push(JSON.parse(resp[i].title_list));

      }
    })
  }

  /*=====================================
  Función que nos avisa cuando finaliza el renderizado de Angular
  ======================================*/
  callback(){
    if(this.render){

      this.render = false;
      let arraySubCategories = [];

      /*=====================================
      Hacemos un recorrido por la lista de títulos
      ======================================*/
      this.arrayTitleList.forEach(titleList =>{

        /*=====================================
        Separar individualmente los títulos
        ======================================*/

        for(let i = 0; i < titleList.length;i++){

          /*=====================================
          Tomamos la colección de las sub-categorías filtrando con la lista de títulos
          ======================================*/

          this.subCategoriesService.getFilterData("title_list", titleList[i])
          .subscribe(resp =>{

            arraySubCategories.push(resp);

            /*=====================================
            Hacemos un recorrido por la colección general de subcategorias
            ======================================*/

            let j;
            let k;
            let arrayTitleName = [];

            for(j in arraySubCategories){

              /*=====================================
              Hacemos un recorrido por la colección particular de subcategorias
              ======================================*/

              for(k in arraySubCategories[j]){

                /*=====================================
                Hacemos un recorrido por la colección particular de subcategorias
                ======================================*/
                arrayTitleName.push(
                  {
                    "titleList": arraySubCategories[j][k].title_list,
                    "subcategory": arraySubCategories[j][k].name,
                    "url": arraySubCategories[j][k].url
                  }
                )
              }
            }

            /*=====================================
            Recorremos el array de objetos nuevo para buscar coincidencias con las listas de título
            ======================================*/

            for(j in arrayTitleName){
              if(titleList[i] == arrayTitleName[j].titleList){

                /*=====================================
                Imprimir el nombre de subcategoría debajo de el listado correspondiente
                ======================================*/

                $(`[titleList='${titleList[i]}']`).append(

                  `<li>
                    <a href="products/${arrayTitleName[j].url}">${arrayTitleName[j].subcategory}</a>
                  </li>`
                )
              }
            }
          })
        }

      })
    }
  }
}
