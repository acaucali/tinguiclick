import { Injectable } from '@angular/core';
import { formatDate } from '@angular/common';
import { Observable, of , throwError} from 'rxjs';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';


import swal from 'sweetalert2';

import { URL_BACKEND } from 'src/app/configuracion/parametros/config';
import { AuthService } from 'src/app/configuracion/parametros/auth.service';




@Injectable()

export class ExcelService {

    private urlEndPoint:string =URL_BACKEND+'api/tinguiclick/pedidos';
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  
    constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }
     
      
  }
  