import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api';
  private torneoUrl = this.baseUrl + '/torneo'; 
  private torneoSquadraUrl = this.baseUrl + '/torneo-squadra';
  private obiettivoUrl = this.baseUrl + '/obiettivo';
  constructor(private http: HttpClient) { }

  getAllTornei(): Observable<any> {
    return this.http.get(this.torneoUrl);
  }

  getTorneoSquadraByIdTorneo(idTorneo: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.torneoSquadraUrl}/${idTorneo}`);
  }

  getObiettivoByIdTorneo(idTorneo: number): Observable<any[]>{
    return this.http.get<any[]>(`${this.obiettivoUrl}/${idTorneo}`);
  }
  
}