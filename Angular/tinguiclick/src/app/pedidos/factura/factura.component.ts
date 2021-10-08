import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Aliados } from 'src/app/aliados/model/aliados';
import { AliadosService } from 'src/app/aliados/model/aliados.service';
import { Domiciliarios } from 'src/app/domiciliarios/model/domiciliarios';
import { DomiciliariosService } from 'src/app/domiciliarios/model/domiciliarios.service';
import { Tarifa } from 'src/app/tarifas/model/tarifa';
import { TarifaService } from 'src/app/tarifas/model/tarifa.service';
import { PedidosService } from '../model/pedidos.service';
import { PedidosComponent } from '../pedidos.component';
import { ModalFacturaService } from './modalfactura.service';
import swal from 'sweetalert2';
import { URL_BACKEND } from 'src/app/configuracion/parametros/config';
import { ExcelService } from '../util/excelservice';

@Component({
  selector: 'factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  private errores: string[];

  public tarifas: Tarifa[];
  public aliados: Aliados[];
  public domiciliarios: Domiciliarios[];

  public fechaInicial: Date;
  public fechafinal: Date;

  titulo: string = "Generar factura";
  constructor(private pedidoService: PedidosService, private router: Router, 
    private activatedRoute: ActivatedRoute, private pedidoComponent: PedidosComponent,
    public modalservice: ModalFacturaService, private domiciliariosService: DomiciliariosService, 
    private aliadosService: AliadosService, private tarifasService: TarifaService, private excelService: ExcelService
   ) { }

  ngOnInit() {
    this.aliadosService.getAliadosList().subscribe(response => this.aliados = response);
    this.domiciliariosService.getDomiciliariosList().subscribe(response => this.domiciliarios = response);
    this.tarifasService.getTarifasList().subscribe(response => this.tarifas = response);

  }
  
  generar(){
    window.open(URL_BACKEND+"api/tinguiclick/pedidos/export"); 
  }

  cerrarModal(){
    this.modalservice.cerrarModal();
  }

}
