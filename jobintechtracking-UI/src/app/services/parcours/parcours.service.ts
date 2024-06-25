import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Parcours } from '../../models/parcours';

@Injectable({
  providedIn: 'root'
})
export class ParcoursService {

  private apiUrl = 'http://localhost:8090/parcours';


  constructor(private http: HttpClient) { }

  getAllParcours(): Observable<Parcours[]> {

    return this.http.get<Array<Parcours>>(this.apiUrl);
  }
}