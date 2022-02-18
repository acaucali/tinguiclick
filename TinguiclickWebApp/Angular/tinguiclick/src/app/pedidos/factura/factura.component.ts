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
import { HttpClient } from '@angular/common/http';
import { AuthService } from 'src/app/configuracion/parametros/auth.service';
import { ExcelserviceService } from '../util/excelservice.service';


@Component({
  selector: 'factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  private errores: string[];
  private data: string[];
  public tarifas: Tarifa[];
  public aliados: Aliados[];
  public domiciliarios: Domiciliarios[];



  fecha: Date;
  fechaFin: Date;
  fechaInicial: string;
  fechaFinal: string;

  titulo: string = "Generar factura";
  constructor(private pedidoService: PedidosService, private router: Router, 
    private activatedRoute: ActivatedRoute, private pedidoComponent: PedidosComponent,
    public modalservice: ModalFacturaService, private domiciliariosService: DomiciliariosService, 
    private aliadosService: AliadosService, private tarifasService: TarifaService,
    private http: HttpClient, private authService: AuthService, private excelService: ExcelserviceService,
   ) { }

  ngOnInit() {
    this.aliadosService.getAliadosList().subscribe(response => this.aliados = response);
    this.domiciliariosService.getDomiciliariosList().subscribe(response => this.domiciliarios = response);
    this.tarifasService.getTarifasList().subscribe(response => this.tarifas = response);

  }
  
  generar(): void{

    let fechaI = (<HTMLInputElement>document.getElementById("fechaInicialFactura")).value;
    let fechaf = (<HTMLInputElement>document.getElementById("fechaFinalFactura")).value;

    this.fecha = new Date(fechaI);
    this.fechaFin = new Date(fechaf);

    let mesIni = this.fecha.getMonth() + parseInt('1');
    let mesFin = this.fechaFin.getMonth() + parseInt('1');
    let stringFecha = this.fecha.getDate()+'-'+mesIni+'-'+this.fecha.getFullYear();
    let stringFechaF = this.fechaFin.getDate()+'-'+mesFin+'-'+this.fechaFin.getFullYear();
    console.log(stringFecha);
    console.log(stringFechaF);
    this.pedidoService.generarExcel(stringFecha, stringFechaF).subscribe(response => {
      this.data = response
      this.excelService.exportASExcelFile(this.data, "factura");
    });
         
  }

  cerrarModal(){
    this.modalservice.cerrarModal();
  }

}


