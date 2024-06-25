import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { StepsService } from '../../services/steps/steps.service';
import { Step } from '../../models/step';


@Component({
  selector: 'app-steps',
  templateUrl: './steps.component.html',
  styleUrls: ['./steps.component.css']
})
export class StepsComponent  implements OnInit{
  stepsList: Step[] = [];
  parcoursId!: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private stepsService: StepsService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.parcoursId = +params['parcoursId'];
      this.loadSteps(this.parcoursId);
      console.log("array1", this.stepsList);
    });
  }

  loadSteps(parcoursId: number): void {
    this.stepsService.getStepsByParcoursId(parcoursId).subscribe(
      (stepData) => {
        this.stepsList = stepData;
        console.log(this.stepsList);
      },
      (error) => {
        console.error('Error fetching steps', error);
      }
    );
  }

  navigateToTask(): void {
    this.router.navigate(['/task']);
  }


}
