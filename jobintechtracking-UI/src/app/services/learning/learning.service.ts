import { Injectable } from '@angular/core';
import { Learning } from '../../models/learning';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LearningService {

  private apiUrl = 'http://localhost:8090/learnings/save';

  constructor(private http: HttpClient) { }

  saveLearning(learning: Learning): Observable<Learning> {
    return this.http.post<Learning>(this.apiUrl, learning);
  }


}