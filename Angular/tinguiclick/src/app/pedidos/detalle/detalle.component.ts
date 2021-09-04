import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Aliados } from 'src/app/aliados/model/aliados';
import { AliadosService } from 'src/app/aliados/model/aliados.service';
import { Domiciliarios } from 'src/app/domiciliarios/model/domiciliarios';
import { DomiciliariosService } from 'src/app/domiciliarios/model/domiciliarios.service';
import swal from 'sweetalert2';
import { Pedido } from '../model/pedido';
import { PedidosService } from '../model/pedidos.service';
import { PedidosComponent } from '../pedidos.component';
import { ModalDetalleDataService} from './modaldetalle.service';

@Component({
  selector: 'detalle-data',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleDataComponent implements OnInit {

  private errores: string[];


  @Input() pedido: Pedido;

  public aliados: Aliados[];
  public domiciliarios: Domiciliarios[];
  
  titulo: string = "Datos del Pedido";
  constructor(private pedidoService: PedidosService, private router: Router, 
    private activatedRoute: ActivatedRoute, private pedidoComponent: PedidosComponent,
    public modalservice: ModalDetalleDataService,  private aliadosService: AliadosService,
    private domiciliariosService: DomiciliariosService,
    
   ) { }

  ngOnInit() {
    this.aliadosService.getAliadosList().subscribe(response => this.aliados = response);
    this.domiciliariosService.getDomiciliariosList().subscribe(response => this.domiciliarios = response);
  }

  cerrarModal(){
    this.modalservice.cerrarModal();
  }

}
