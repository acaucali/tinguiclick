import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Aliados } from 'src/app/aliados/model/aliados';
import { AliadosService } from 'src/app/aliados/model/aliados.service';
import { DocumentoService } from 'src/app/configuracion/documentos/documento.service';
import { Domiciliarios } from 'src/app/domiciliarios/model/domiciliarios';
import { DomiciliariosService } from 'src/app/domiciliarios/model/domiciliarios.service';
import { Tarifa } from 'src/app/tarifas/model/tarifa';
import { TarifaService } from 'src/app/tarifas/model/tarifa.service';
import swal from 'sweetalert2';
import { Pedido } from '../model/pedido';
import { PedidosService } from '../model/pedidos.service';
import { PedidosComponent } from '../pedidos.component';
import { ModalPedidosService } from './modalpedidos.service';

@Component({
  selector: 'detalle-pedido',
  templateUrl: './detalle-pedido.component.html',
  styleUrls: ['./detalle-pedido.component.css']
})
export class DetallePedidoComponent implements OnInit {

  private errores: string[];


  @Input() pedido: Pedido;

  public tarifas: Tarifa[];
  public aliados: Aliados[];
  public domiciliarios: Domiciliarios[];

  /*
  public documento: Documento;
  private archivoSeleccionado: File;
  
 
  private urlEndPoint:string =URL_BACKEND+'api/tinguiclick/documento';
  */

  titulo: string = "Datos del Pedido";
  constructor(private pedidoService: PedidosService, private router: Router, 
    private activatedRoute: ActivatedRoute, private pedidoComponent: PedidosComponent,
    public modalservice: ModalPedidosService, private documentoservice: DocumentoService,
    private domiciliariosService: DomiciliariosService, private aliadosService: AliadosService,
    private tarifasService: TarifaService
   ) { }

  ngOnInit() {
    this.aliadosService.getAliadosList().subscribe(response => this.aliados = response);
    this.domiciliariosService.getDomiciliariosList().subscribe(response => this.domiciliarios = response);
    this.tarifasService.getTarifasList().subscribe(response => this.tarifas = response);

  }

  
 
  create(): void{
    console.log(this.pedido);
    this.pedidoService.create(this.pedido).subscribe(
      json => {
      swal.fire('Nuevo Pedido', `${json.mensaje}`, 'success');
      this.cerrarModal();
      this.pedidoComponent.getPedidos();
    },
    err =>{
      this.errores = err.error.errors as string[];
      console.error('Código error: '+err.status);
      console.error(err.error.errors);
    }
    );
  }

  update(): void{
    this.pedidoService.update(this.pedido).subscribe(json =>{
      swal.fire('Pedido Actualizado',  `${json.mensaje}`, 'success')
      this.cerrarModal();
    },
    err =>{
      this.errores = err.error.errors as string[];
      console.error('Código error: '+err.status);
      console.error(err.error.errors);
    }
    );
  }

  cerrarModal(){
    this.modalservice.cerrarModal();
  }

}
