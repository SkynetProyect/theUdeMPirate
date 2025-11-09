import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { MyDocument } from '../model/Document';


@Injectable({
  providedIn: 'root' // o en providers del módulo si quieres scope limitado
})
export class DocumentsService {
  // Ajusta la URL base a tu API
  private apiUrl = environment.apiUrl;  // por ejemplo: http://backend:8080

  constructor(private http: HttpClient) {}

  /**
   * GET /documents
   * Obtiene la lista de documentos públicos
   */
  getAll(): Observable<MyDocument[]> {
    return this.http.get<MyDocument[]>(`${this.apiUrl}/api/document`);
  }

  getById(id: number): Observable<MyDocument> {
    return this.http.get<MyDocument>(`${this.apiUrl}/api/document/${id}`);
  }

  getByName(nombre: string): Observable<MyDocument[]> {
    return this.http.get<MyDocument[]>(`${this.apiUrl}/api/document/byName/${nombre}`);
  }

  getByAuthor(nombre: string): Observable<MyDocument[]> {
    return this.http.get<MyDocument[]>(`${this.apiUrl}/api/document/byAuthor/${nombre}`);
  }

  getByCategory(nombre: Number): Observable<MyDocument[]> {
    return this.http.get<MyDocument[]>(`${this.apiUrl}/api/document/byCategory/${nombre}`);
  }

  save(document: MyDocument): Observable<MyDocument> {
    return this.http.post<MyDocument>(`${this.apiUrl}/api/document`, document);
  }

}
