import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly apiUrl = '/api/users';

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  createUser(payload: Omit<User, 'id' | 'roleName'>): Observable<User> {
    return this.http.post<User>(this.apiUrl, payload);
  }

  updateUser(id: number, payload: Omit<User, 'id' | 'roleName'>): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${id}`, payload);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
