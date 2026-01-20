import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role } from '../models/role.model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private readonly apiUrl = '/api/roles';

  constructor(private http: HttpClient) {}

  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(this.apiUrl);
  }

  createRole(payload: Omit<Role, 'id'>): Observable<Role> {
    return this.http.post<Role>(this.apiUrl, payload);
  }

  updateRole(id: number, payload: Omit<Role, 'id'>): Observable<Role> {
    return this.http.put<Role>(`${this.apiUrl}/${id}`, payload);
  }

  deleteRole(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
