;
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router'; 
import { DataService} from '../../services/DataService/data-service.service'; 
import { Step } from '../../models/step';
import { Observable } from 'rxjs';
import { Learning } from '../../models/learning';
import { Doing } from '../../models/doing';



@Component({
  selector: 'app-learning-doing',
  templateUrl: './learning-doing.component.html',
  styleUrls: ['./learning-doing.component.css']
})
export class LearningDoingComponent implements OnInit {
  private apiLearning = 'http://localhost:8090';
  learnings: Learning[] = [];
  doings: Doing[] = [];
  currentStep: Step | null = null;
  studentId: number = 1; 
  currentTaskUrl: string = ''; 

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router, // Inject Router
    private dataService: DataService  // Inject DataService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam !== null) {
        const stepsId = +idParam;  // Convert idParam to number
        console.log(`Retrieved stepsId from route params: ${stepsId}`);

        // Fetch data using stepsId
        this.getLearning(stepsId).subscribe(data => {
          this.learnings = data;
          console.log('Retrieved learnings:', this.learnings);

          // Assuming you want to set currentStep to the first step with matching ID
          const matchingStep = this.learnings.flatMap(learning => learning.steps).find(step => step.id === stepsId);
          if (matchingStep) {
            this.currentStep = matchingStep;
            console.log('Set currentStep:', this.currentStep);
          } else {
            console.error(`Step with id ${stepsId} not found in learnings.`);
          }
        }, error => {
          console.error('Error fetching learnings:', error);
        });

        this.getDoing(stepsId).subscribe(data => {
          this.doings = data;
          console.log('Retrieved doings:', this.doings);
        }, error => {
          console.error('Error fetching doings:', error);
        });
      }
    });
  }

  getLearning(stepsId: number): Observable<Learning[]> {
    return this.http.get<Learning[]>(`${this.apiLearning}/learnings?stepsId=${stepsId}`);
  }

  getDoing(stepsId: number): Observable<Doing[]> {
    return this.http.get<Doing[]>(`${this.apiLearning}/doings?stepsId=${stepsId}`);
  }

  completeStep(step: Step | null, taskUrl: string): void {
    if (!step) {
      console.error('Step is null or undefined.');
      return;
    }

    this.dataService.completeStep(this.studentId, step.id, taskUrl).subscribe(() => {
      step.completed = true;
      console.log('Completed step:', step);
      this.router.navigate(['/step-progress']);
    }, error => {
      console.error('Error completing step:', error);
    });
  }
}