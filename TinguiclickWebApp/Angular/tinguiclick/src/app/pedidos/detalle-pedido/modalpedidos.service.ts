import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalPedidosService {

  modal: boolean = false;
  notificarUpload= new EventEmitter<any>();
  constructor() { }
  abrirModal(){
    this.modal= true;
  }

  cerrarModal(){
    this.modal= false;
  }
}
