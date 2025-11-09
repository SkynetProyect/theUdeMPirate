import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Materia } from '../model/Materia';


@Injectable({
  providedIn: 'root' // o en providers del módulo si quieres scope limitado
})
export class MateriaService {
  // Ajusta la URL base a tu API
  private apiUrl = environment.apiUrl;  // por ejemplo: http://backend:8080

  constructor(private http: HttpClient) {}

  /**
   * GET /category "materias"
   * Obtiene la lista de materias públicos
   */
  getAll(): Observable<Materia[]> {
    return this.http.get<Materia[]>(`${this.apiUrl}/api/category`);
  }



}
