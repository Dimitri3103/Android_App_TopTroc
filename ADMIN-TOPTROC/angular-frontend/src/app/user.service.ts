import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { User } from './user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:81/api/v1/users";

  constructor(private httpClient: HttpClient) { }

  getUsersList(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}`);
  }

  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, user);
  }

  getUserById(user_id: number): Observable<User>{
    return this.httpClient.get<User>(`${this.baseURL}/${user_id}`);
  }

  updateUser(user_id: number, user: User): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${user_id}`, user);
  }

  deleteUser(user_id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${user_id}`);
  }
}
