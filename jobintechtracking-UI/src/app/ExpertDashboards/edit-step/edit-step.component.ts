import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StepsService } from '../../services/steps/steps.service';
import { Step } from '../../models/step';
import { Learning } from '../../models/learning';
import { Doing } from '../../models/doing';

@Component({
  selector: 'app-edit-step',
  templateUrl: './edit-step.component.html',
  styleUrls: ['./edit-step.component.css']
})
export class EditStepComponent  {
  stepId!: number;
  step: Step | null = null;
  learning: Learning | null = null;
  doing: Doing | null = null;
  isLoading = true;
  editingField: string | null = null;

  constructor(private route: ActivatedRoute, private stepsService: StepsService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('stepId');
      if (id !== null) {
        this.stepId = parseInt(id, 10);
        this.loadStepDetails(this.stepId);
      }
    });
  }

  loadStepDetails(stepId: number): void {
    this.stepsService.getStepWithLearningAndDoing(stepId).subscribe(
      (response: any) => {
        this.step = response.step;
        this.learning = response.learning;
        this.doing = response.doing;
        this.isLoading = false;
      },
      (error) => {
        console.error('Error fetching step details:', error);
        this.isLoading = false;
      }
    );
  }

  startEditing(field: string): void {
    this.editingField = field; 
  }

  updateField(event: Event): void {
    if (this.step && this.editingField) {
      const newValue = (event.target as HTMLInputElement).value; 
      if (this.editingField === 'title') {
        this.step.title = newValue;
      } else if (this.editingField === 'description') {
        this.step.description = newValue;
      } else if (this.editingField === 'duration') {
        this.step.durationInMinutes = parseInt(newValue, 10);
      } else if (this.editingField === 'learningTitle'){
        this.learning!.title = newValue;
      }else if(this.editingField === 'learningDescription'){
         this.learning?.description === newValue;
      
      }else if(this.editingField === 'task'){
         this.learning?.description === newValue;
      }
    

      // Update step, learning, and doing using the service method
      this.stepsService.updateStepWithLearningAndDoing(this.step, this.learning, this.doing).subscribe(
        () => {
          console.log('Step, learning, and doing updated successfully');
          this.editingField = null; // Exit edit mode
        },
        (error) => {
          console.error('Error updating step, learning, and doing:', error);
        }
      );
    }
  }
}

