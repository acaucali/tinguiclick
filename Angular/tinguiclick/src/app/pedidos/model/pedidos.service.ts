import { Injectable } from '@angular/core';
import { formatDate } from '@angular/common';
import { Observable, of , throwError} from 'rxjs';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';


import swal from 'sweetalert2';

import { URL_BACKEND } from 'src/app/configuracion/parametros/config';
import { AuthService } from 'src/app/configuracion/parametros/auth.service';
import { Pedido } from './pedido';



@Injectable()

export class PedidosService {

    private urlEndPoint:string =URL_BACKEND+'api/tinguiclick/pedidos';
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    public pedidos: Pedido[];
  
    constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }
  
    getPedidosList(){
      return this.http.get(this.urlEndPoint).pipe(
        map(res =>{
        this.pedidos = res as Pedido[];
        return this.pedidos;
        })
      );
    }

     
    create(pedido: Pedido) : Observable<any>{
      console.log(pedido);
      return this.http.post<any>(this.urlEndPoint, pedido).pipe(
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
  
    getPedido(id): Observable<Pedido>{
      return this.http.get<Pedido>(`${this.urlEndPoint}/${id}`).pipe(
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
  
    update(pedido: Pedido): Observable<any>{
      return this.http.put<any>(`${this.urlEndPoint}/${pedido.pedidoId}`, pedido).pipe(
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
  
    delete(id: number): Observable<Pedido>{
      return this.http.delete<Pedido>(`${this.urlEndPoint}/${id}`).pipe(
        catchError(e =>{
          if(e.error.mensaje){ 
            console.error(e.error.mensaje);
            swal.fire(e.error.mensaje, e.error.error, 'error');
          }           
          return throwError(e);
        })
      );
    }

    recibido(pedido: Pedido): Observable<any>{
      return this.http.put<any>(`${this.urlEndPoint}/recibido/${pedido.pedidoId}`, pedido).pipe(
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
  
    entregado(pedido: Pedido): Observable<any>{
      return this.http.put<any>(`${this.urlEndPoint}/entregado/${pedido.pedidoId}`, pedido).pipe(
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

      
  }
  