import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";
import { Observable, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import swal from 'sweetalert2';
import { URL_BACKEND } from "../config";
import { TipoIdentificacion } from "./tipoIdentificacion";
import { Injectable } from '@angular/core';

@Injectable()

export class TipoIdentificacionService {

    private urlEndPoint:string =URL_BACKEND+'api/tinguiclick/tipoIdentificacion';
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    public tiposId: TipoIdentificacion[];
  
    constructor(private http: HttpClient, private router: Router) { }
    
    
  
    getTiposIdentificacionList(){
      return this.http.get(this.urlEndPoint).pipe(
        map(res =>{
        this.tiposId = res as TipoIdentificacion[];
        return this.tiposId;
        })
      );
    }

     
    create(tipoId: TipoIdentificacion) : Observable<any>{
      console.log(tipoId);
      return this.http.post<any>(this.urlEndPoint, tipoId).pipe(
        catchError(e =>{
          
          if(e.status==400){
            return throwError(e);
          }
          if(e.error.mensaje){ 
            console.error(e.error.mensaje);
            swal.fire(e.error.mensaje, e.error.error, 'error');
          }          
          return throwError(e);
        })
      );
    }
  
    getMedico(id): Observable<TipoIdentificacion>{
      return this.http.get<TipoIdentificacion>(`${this.urlEndPoint}/${id}`).pipe(
        catchError(e=>{
          if(e.status != 401 && e.error.mensaje){
            this.router.navigate(['/usuarios']);
            console.error(e.error.mensaje);
            swal.fire(e.error.mensaje, e.error.error, 'error');
          }          
          return throwError(e);
        })
      );
    }
  
    update(tipoId: TipoIdentificacion): Observable<any>{
      return this.http.put<any>(`${this.urlEndPoint}/${tipoId.tipoIdentificacionId}`, tipoId).pipe(
        catchError(e =>{
          if(e.status==400){
            return throwError(e);
          }
          if(e.error.mensaje){ 
            console.error(e.error.mensaje);
            swal.fire(e.error.mensaje, e.error.error, 'error');
          }           
          return throwError(e);
        })
      );
    }
  
    delete(id: number): Observable<TipoIdentificacion>{
      return this.http.delete<TipoIdentificacion>(`${this.urlEndPoint}/${id}`).pipe(
        catchError(e =>{
          if(e.error.mensaje){ 
            console.error(e.error.mensaje);
            swal.fire(e.error.mensaje, e.error.error, 'error');
          }           
          return throwError(e);
        })
      );
    }
  
  
  }
  