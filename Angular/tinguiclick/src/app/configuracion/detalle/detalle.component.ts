import { Component, OnInit, Input } from '@angular/core';
import swal from 'sweetalert2';

import { Router, ActivatedRoute } from '@angular/router';

import { ModalUsuarioService } from './modalusuario.service';
import { Usuario } from '../usuarios/usuario';
import { UsuariosService } from '../usuarios/usuario.service';
import { ConfigComponent } from '../archivosinterfaz/config.component';
import { TipoIdentificacion } from '../parametros/tablas/tipoIdentificacion';
import { TipoIdentificacionService } from '../parametros/tablas/tipoIdentificacionService';

@Component({
  selector: 'detalle-usuario',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  private errores: string[];


  @Input() usuario: Usuario;
 
  public tiposId: TipoIdentificacion[];

  titulo: string = "Datos del Usuario";
  constructor(private usuariosService: UsuariosService, private router: Router, 
    private activatedRoute: ActivatedRoute, private usuarioComponent: ConfigComponent,
    public modalservice: ModalUsuarioService, private tipoIdService: TipoIdentificacionService
   ) { }

  ngOnInit() {
    this.tipoIdService.getTiposIdentificacionList().subscribe(response => this.tiposId = response);
  }

  
 
  create(): void{
    console.log(this.usuario);
    this.usuariosService.create(this.usuario).subscribe(
      json => {
      swal.fire('Nuevo Usuario', `${json.mensaje}`, 'success');
      this.cerrarModal();
      this.usuarioComponent.getUsuarios();
    },
    err =>{
      this.errores = err.error.errors as string[];
      console.error('Código error: '+err.status);
      console.error(err.error.errors);
    }
    );
  }

  update(): void{
    this.usuariosService.update(this.usuario).subscribe(json =>{
      swal.fire('Usuario Actualizado',  `${json.mensaje}`, 'success')
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
