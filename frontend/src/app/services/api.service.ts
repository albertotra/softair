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
  private punteggioUrl = this.baseUrl + '/punteggio';
  private squadraUrl = this.baseUrl + '/squadra';
  private classificaUrl = this.baseUrl + '/classifica';
  constructor(private http: HttpClient) { }

  getAllTornei(): Observable<any> {
    return this.http.get(this.torneoUrl);
  }

  getTorneoSquadraByIdTorneo(idTorneo: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.torneoSquadraUrl}/${idTorneo}`);
  }

  getObiettiviByIdTorneo(idTorneo: number, idSquadra: number): Observable<any[]>{
    return this.http.get<any[]>(`${this.obiettivoUrl}/torneo/${idTorneo}/${idSquadra}`);
  }

  getPunteggioByIdPunteggio(idPunteggio: number): Observable<any>{
    return this.http.get<any>(`${this.punteggioUrl}/${idPunteggio}`);
  }

  getObiettivoByIdObiettivo(idObiettivo: number): Observable<any[]>{
    return this.http.get<any[]>(`${this.obiettivoUrl}/${idObiettivo}`);
  }

  getSquadraById(id: number): Observable<any[]>{
    return this.http.get<any[]>(`${this.squadraUrl}/${id}`);
  }

  salvaPunteggio(obj: any): Observable<any> {
    return this.http.post(`${this.punteggioUrl}/salva`, obj);
  }

  getTorneoById(idTorneo: number): Observable<any> {
    return this.http.get(`${this.torneoUrl}/${idTorneo}`);
  }

  // calcolaClassifica(idTorneo: number): Observable<any> {
  //   return this.http.post(`${this.classificaUrl}/calcola/${idTorneo}`);
  // }

  getClassificaByIdTorneo(idTorneo: number): Observable<any> {
    return this.http.get(`${this.classificaUrl}/${idTorneo}`);
  }
  
}