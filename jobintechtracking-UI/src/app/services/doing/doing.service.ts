import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Doing } from '../../models/doing';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DoingService {
  private apiUrl = 'http://localhost:8090/doings/save';

  constructor(private http: HttpClient) { }

  saveDoing(doing: Doing): Observable<Doing> {
    return this.http.post<Doing>(this.apiUrl, doing);
  }
}