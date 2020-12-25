import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { ObjectModel } from './objectmodel';

@Injectable({
  providedIn: 'root'
})
export class ObjectService {

  private baseURL = "http://localhost:81/api/v1/objects";

  constructor(private httpClient: HttpClient) { }

  getObjectsList(): Observable<ObjectModel[]>{
    return this.httpClient.get<ObjectModel[]>(`${this.baseURL}`);
  }

  createObject(objectModel: ObjectModel): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, objectModel);
  }

  getObjectById(id: number): Observable<ObjectModel>{
    return this.httpClient.get<ObjectModel>(`${this.baseURL}/${id}`);
  }

  updateObject(id: number, objectModel: ObjectModel): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, objectModel);
  }

  deleteObject(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
