import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Autor } from '../model/Autor';


@Injectable({
  providedIn: 'root' // o en providers del módulo si quieres scope limitado
})
export class autorService {
  // Ajusta la URL base a tu API
  private apiUrl = environment.apiUrl;  // por ejemplo: http://backend:8080

  constructor(private http: HttpClient) {}

  /**
   * GET /authors
   * Obtiene la lista de autores públicos
   */
  getAll(): Observable<Autor[]> {
    return this.http.get<Autor[]>(`${this.apiUrl}/api/author`);
  }



}
