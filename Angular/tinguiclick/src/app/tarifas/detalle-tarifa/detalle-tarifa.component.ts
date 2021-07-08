import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tarifa } from '../model/tarifa';
import { TarifaService } from '../model/tarifa.service';
import { TarifasComponent } from '../tarifas.component';
import { ModalTarifaService } from './modaltarifa.service';
import swal from 'sweetalert2';

@Component({
  selector: 'detalle-tarifa',
  templateUrl: './detalle-tarifa.component.html',
  styleUrls: ['./detalle-tarifa.component.css']
})
export class DetalleTarifaComponent implements OnInit {

  private errores: string[];

  @Input() tarifa: Tarifa;
 
  titulo: string = "Datos de la Tarifa";
  constructor(private tarifaService: TarifaService, private router: Router, 
    private activatedRoute: ActivatedRoute, private tarifaComponent: TarifasComponent,
    public modalservice: ModalTarifaService
   ) { }

  ngOnInit() {
  
  }

  create(): void{
    console.log(this.tarifa);
    this.tarifaService.create(this.tarifa).subscribe(
      json => {
      swal.fire('Nueva Tarifa', `${json.mensaje}`, 'success');
      this.cerrarModal();
      this.tarifaComponent.getTarifas();
    },
    err =>{
      this.errores = err.error.errors as string[];
      console.error('Código error: '+err.status);
      console.error(err.error.errors);
    }
    );
  }

  update(): void{
    this.tarifaService.update(this.tarifa).subscribe(json =>{
      swal.fire('Tarifa Actualizado',  `${json.mensaje}`, 'success')
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
