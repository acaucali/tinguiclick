import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../configuracion/parametros/auth.service';
import { ModalTarifaService } from './detalle-tarifa/modaltarifa.service';
import { Tarifa } from './model/tarifa';
import { TarifaService } from './model/tarifa.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-tarifas',
  templateUrl: './tarifas.component.html',
  styleUrls: ['./tarifas.component.css']
})
export class TarifasComponent implements OnInit {

  pageTar: number =1;

  tarifas: Tarifa[];
  paginador: any;
  tarifaSeleccionado: Tarifa;

  elements: any = [];
  previous: any = [];

  firstItemIndex;
  lastItemIndex;

  constructor(private tarifaService: TarifaService ,public modalservice: ModalTarifaService, 
    public authService: AuthService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.getTarifas();
  }

  delete(tarifa: Tarifa): void{
    swal.fire({
      title: 'Está seguro?',
      text:  `¿Seguro que desea eliminar la tarifa ${tarifa.ubicacion} ?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33'
    }).then((result) => {
      if (result.value) {
        this.tarifaService.delete(tarifa.tarifaId).subscribe(
          response =>{
            this.getTarifas();
            swal.fire(
              'Tarifa eliminada!',
              'La Tarifa se ha eliminado con éxito',
              'success'
            )
          }
        )
        
      }
    })
  }
  
  abrirModal(tarifa: Tarifa){
    this.tarifaSeleccionado= tarifa;
    this.modalservice.abrirModal();
  }

  crearTarifa(){
    this.tarifaSeleccionado = new Tarifa();
    this.modalservice.abrirModal();
  }
  
  getTarifas(){
    this.tarifas = null;
    this.elements = [];
    this.previous = [];
    this.tarifaService.getTarifasList().subscribe(response =>{
      this.tarifas = response;
      if(this.tarifas.length >0){
        this.tarifas.forEach(tar =>{
          this.elements.push({

            tarifaId: tar.tarifaId, ubicacion: tar.ubicacion,
            valor: tar.valor

            });
        });
      }
      
    });
  }

}
