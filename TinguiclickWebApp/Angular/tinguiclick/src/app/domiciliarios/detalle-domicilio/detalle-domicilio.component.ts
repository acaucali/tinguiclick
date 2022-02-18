import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TipoIdentificacion } from 'src/app/configuracion/parametros/tablas/tipoIdentificacion';
import { TipoIdentificacionService } from 'src/app/configuracion/parametros/tablas/tipoIdentificacionService';
import { Usuario } from 'src/app/configuracion/usuarios/usuario';
import { UsuariosService } from 'src/app/configuracion/usuarios/usuario.service';
import { DomiciliariosComponent } from '../domiciliarios.component';
import { DomiciliariosService } from '../model/domiciliarios.service';
import { ModalDomiciliariosService } from './modaldomiciliarios.service';
import swal from 'sweetalert2';
import { Domiciliarios } from '../model/domiciliarios';

@Component({
  selector: 'detalle-domicilio',
  templateUrl: './detalle-domicilio.component.html',
  styleUrls: ['./detalle-domicilio.component.css']
})
export class DetalleDomicilioComponent implements OnInit {

  private errores: string[];


  @Input() domiciliario: Domiciliarios;
 
  public tiposId: TipoIdentificacion[];
  public usuarios: Usuario[];

  titulo: string = "Datos del Domiciliario";
  constructor(private usuariosService: UsuariosService, private router: Router, 
    private activatedRoute: ActivatedRoute, private domiciliarioComponent: DomiciliariosComponent,
    public modalservice: ModalDomiciliariosService, private tipoIdService: TipoIdentificacionService,
    private domiciliariosService: DomiciliariosService
   ) { }

  ngOnInit() {
    this.tipoIdService.getTiposIdentificacionList().subscribe(response => this.tiposId = response);
    this.usuariosService.getUsuariosList().subscribe(response => this.usuarios = response);
  }

  
 
  create(): void{
    console.log(this.domiciliario);
    this.domiciliariosService.create(this.domiciliario).subscribe(
      json => {
      swal.fire('Nuevo Domiciliario', `${json.mensaje}`, 'success');
      this.cerrarModal();
      this.domiciliarioComponent.getDomiciliarios();
    },
    err =>{
      this.errores = err.error.errors as string[];
      console.error('Código error: '+err.status);
      console.error(err.error.errors);
    }
    );
  }

  update(): void{
    this.domiciliariosService.update(this.domiciliario).subscribe(json =>{
      swal.fire('Domiciliario Actualizado',  `${json.mensaje}`, 'success')
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
