import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../models/client.model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private readonly apiUrl = '/api/clients';

  constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl);
  }

  createClient(payload: Omit<Client, 'id'>): Observable<Client> {
    return this.http.post<Client>(this.apiUrl, payload);
  }

  updateClient(id: number, payload: Omit<Client, 'id'>): Observable<Client> {
    return this.http.put<Client>(`${this.apiUrl}/${id}`, payload);
  }

  deleteClient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
