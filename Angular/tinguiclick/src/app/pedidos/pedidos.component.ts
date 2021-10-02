import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../configuracion/parametros/auth.service';
import { ModalPedidosService } from './detalle-pedido/modalpedidos.service';

import { Pedido } from './model/pedido';
import swal from 'sweetalert2';
import { PedidosService } from './model/pedidos.service';
import { ModalDetalleDataService } from './detalle/modaldetalle.service';
import { Aliados } from '../aliados/model/aliados';
import { AliadosService } from '../aliados/model/aliados.service';
import { Domiciliarios } from '../domiciliarios/model/domiciliarios';
import { DomiciliariosService } from '../domiciliarios/model/domiciliarios.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit {

  pagePed: number =1;

  pedidos: Pedido[];
  paginador: any;
  pedidoSeleccionado: Pedido;

  public aliados: Aliados[];
  public domiciliarios: Domiciliarios[];

  public fechaInicio: Date;
  public fechaFinal: Date;
  public aliado: number;
  public domi: number;

  elements: any = [];
  previous: any = [];


  firstItemIndex;
  lastItemIndex;

  constructor(private pedidosService: PedidosService ,public modalservice: ModalPedidosService, 
    public modalDetalle: ModalDetalleDataService,
    public authService: AuthService, private aliadosService: AliadosService, private domiciliariosService: DomiciliariosService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.aliadosService.getAliadosList().subscribe(response => this.aliados = response);
    this.domiciliariosService.getDomiciliariosList().subscribe(response => this.domiciliarios = response);
    this.getPedidos();
  }

  delete(pedido: Pedido): void{
    swal.fire({
      title: 'Está seguro?',
      text:  `¿Seguro que desea eliminar el pedido?`,
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
        this.pedidosService.delete(pedido.pedidoId).subscribe(
          response =>{
            this.getPedidos();
            swal.fire(
              'Pedido eliminado!',
              'El pedido se ha eliminado con éxito',
              'success'
            )
          }
        )
        
      }
    })
  }

  recibido(pedido: Pedido): void{
    swal.fire({
      title: '¿Ya recibiste el pedido?',
      text:  ``,
      type: 'warning',
      showCancelButton: true,
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      confirmButtonText: 'Si, ya lo recibi!',
      cancelButtonText: 'No, estoy esperando!',
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33'
    }).then((result) => {
      if (result.value) {
        this.pedidosService.recibido(pedido).subscribe(
          response =>{
            this.getPedidos();
            swal.fire(
              'Pedido recibido!',
              'El pedido se ha asignado con éxito',
              'success'
            )
          }
        )
        
      }
    })
  }

  entregado(pedido: Pedido): void{
    swal.fire({
      title: '¿Ya se entrego el pedido?',
      text:  ``,
      type: 'warning',
      showCancelButton: true,
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      confirmButtonText: 'Si, entregado!',
      cancelButtonText: 'No, estoy en ello!',
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33'
    }).then((result) => {
      if (result.value) {
        this.pedidosService.entregado(pedido).subscribe(
          response =>{
            this.getPedidos();
            swal.fire(
              'Pedido entregado!',
              'El pedido se ha entregado con éxito',
              'success'
            )
          }
        )
        
      }
    })
  }
  
  abrirModal(pedido: Pedido){
    this.pedidoSeleccionado= pedido;
    this.modalservice.abrirModal();
  }

  detalle(pedido: Pedido){
    this.pedidoSeleccionado= pedido;
    this.modalDetalle.abrirModal();
  }

  crearPedidos(){
    this.pedidoSeleccionado = new Pedido();
    this.modalservice.abrirModal();
  }

  generarFacturas(){
    
  }
  
  getPedidos(){
    this.pedidos = null;
    this.elements = [];
    this.previous = [];
    this.pedidosService.getPedidosList().subscribe(response =>{
      this.pedidos = response;
      if(this.pedidos.length >0){
        this.pedidos.forEach(ped =>{
          this.elements.push({
            pedidoId: ped.pedidoId, nombreCliente: ped.nombreCliente, apellidoCliente: ped.apellidoCliente, direccionCliente: ped.direccionCliente,
            numeroCelular: ped.numeroCelular, telefono: ped.telefono, municipio: ped.municipio, ciudad: ped.ciudad, metodoPago: ped.metodoPago,
            detalle: ped.detalle, observacion: ped.observacion, valor: ped.valor, alerta: ped.alerta, estado: ped.estado, tipo: ped.tipo,
            tarifa: ped.tarifa, aliado: ped.aliado, domiciliario: ped.domiciliario, fechaRegistro: ped.fechaRegistro, fechaModificacion: ped.fechaModificacion
            
            });
        });
      }
      
    });
  }

}
