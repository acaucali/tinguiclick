import { Injectable } from '@angular/core';
import { formatDate } from '@angular/common';
import { Observable, of , throwError} from 'rxjs';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';


import swal from 'sweetalert2';

import { URL_BACKEND } from 'src/app/configuracion/parametros/config';
import { AuthService } from 'src/app/configuracion/parametros/auth.service';
import { Tarifa } from './tarifa';



@Injectable()

export class TarifaService {

    private urlEndPoint:string =URL_BACKEND+'api/tinguiclick/tarifa';
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    public tarifas: Tarifa[];
  
    constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }
  
    getTarifasList(){
      return this.http.get(this.urlEndPoint).pipe(
        map(res =>{
        this.tarifas = res as Tarifa[];
        return this.tarifas;
        })
      );
    }

     
    create(tarifa: Tarifa) : Observable<any>{
      console.log(tarifa);
      return this.http.post<any>(this.urlEndPoint, tarifa).pipe(
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
  
    getTarifa(id): Observable<Tarifa>{
      return this.http.get<Tarifa>(`${this.urlEndPoint}/${id}`).pipe(
        catchError(e=>{
          if(e.status != 401 && e.error.mensaje){
            this.router.navigate(['/login']);
            console.error(e.error.mensaje);
            swal.fire(e.error.mensaje, e.error.error, 'error');
          }          
          return throwError(e);
        })
      );
    }
  
    update(tarifa: Tarifa): Observable<any>{
      return this.http.put<any>(`${this.urlEndPoint}/${tarifa.tarifaId}`, tarifa).pipe(
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
  
    delete(id: number): Observable<Tarifa>{
      return this.http.delete<Tarifa>(`${this.urlEndPoint}/${id}`).pipe(
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
  