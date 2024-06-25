import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { Step } from '../../models/step';
import { Learning } from '../../models/learning';
import { Doing } from '../../models/doing';

@Injectable({
  providedIn: 'root'
})
export class StepsService {


  private apiUrl = 'http://localhost:8090/steps';
  private learningUrl = 'http://localhost:8090/learnings';
  private doingUrl = 'http://localhost:8090/doings';


  constructor(private http: HttpClient) { }

  saveStep(step: Step): Observable<Step> {
    return this.http.post<Step>(this.apiUrl, step);
  }

  getStepsByParcoursId(parcoursId: number): Observable<Step[]> {
    return this.http.get<Step[]>(`http://localhost:8090/steps/parcours/${parcoursId}`);
  }
    // New method to get step details by ID
    getStepWithLearningAndDoing(stepId: number): Observable<any> {
      const step$ = this.http.get<Step>(`${this.apiUrl}/${stepId}`);
      const learning$ = this.http.get<Learning[]>(`${this.learningUrl}/${stepId}`);
      const doing$ = this.http.get<Doing[]>(`${this.doingUrl}/${stepId}`);
  
      return forkJoin({ step: step$, learning: learning$, doing: doing$ });
    }
    updateStepWithLearningAndDoing(step: Step, learning: any, doing: any): Observable<any> {
      const updateStep$ = this.http.put(`${this.apiUrl}/${step.id}`, step);
      const updateLearning$ = this.http.put(`${this.learningUrl}/${step.id}`, learning);
      const updateDoing$ = this.http.put(`${this.doingUrl}/${step.id}`, doing);
  
      return forkJoin([updateStep$, updateLearning$, updateDoing$]);
    }
}