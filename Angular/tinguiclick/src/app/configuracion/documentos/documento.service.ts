import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { Documento } from './documento';
import { catchError, map } from 'rxjs/operators';
import swal from 'sweetalert2';
import { URL_BACKEND } from '../parametros/config';


@Injectable({
  providedIn: 'root'
})
export class DocumentoService {

  private urlEndPoint:string =URL_BACKEND+'api/tinguiclick/documento';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  


  constructor(private http: HttpClient, private router: Router) { }

  

  subirDocumento(origenId, archivo: File) : Observable<any>{

    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", origenId);
  
    return this.http.post<any>(`${this.urlEndPoint}/upload`, formData).pipe(
      catchError(e=>{
        console.error(e.error.mensaje);
        swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  getDocumento(id): Observable<Documento>{
    return this.http.get<Documento>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e=>{
        this.router.navigate(['/procesos']);
        console.error(e.error.mensaje);
        swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  
  
  deleteDocumento(id: number): Observable<Documento>{
    return this.http.delete<Documento>(`${this.urlEndPoint}/eliminar/${id}`,{headers: this.httpHeaders }).pipe(
      catchError(e =>{
        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

}
