import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api'; // URL del backend Spring Boot
  private torneoUrl = this.baseUrl + '/torneo';
  constructor(private http: HttpClient) { }

  getAllTornei(): Observable<any> {
    return this.http.get(this.torneoUrl);
  }
  
}