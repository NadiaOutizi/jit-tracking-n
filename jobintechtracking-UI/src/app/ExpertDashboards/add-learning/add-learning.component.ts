import { Component, OnInit } from '@angular/core';
import { Step } from '../../models/step';
import { Router } from '@angular/router';
import { LearningService } from '../../services/learning/learning.service';
import { Learning } from '../../models/learning';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-learning',
  templateUrl: './add-learning.component.html',
  styleUrls: ['./add-learning.component.css']
})

export class AddLearningComponent implements OnInit {

  learningForm!: FormGroup;
  step!: Step;
  learning!: Learning;

  constructor(private router: Router,
    private learningService: LearningService,
    private fb: FormBuilder) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state) {
      this.step = navigation.extras.state['step'];
    }
    this.learningForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      urls: this.fb.array([this.fb.group({ url: ['', Validators.required] })])
    });
  }

  ngOnInit(): void {
    this.learning = {
      id: 1,
      title: '',
      description: '',
      url: [],
      steps: this.step
    }
    console.log(this.step);
  }

  get urls() {
    return this.learningForm.get('urls') as FormArray;
  }

  addUrl(): void {
    this.urls.push(this.fb.group({ url: ['', Validators.required] }));
  }

  removeUrl(index: number): void {
    this.urls.removeAt(index);
  }

  saveLearning(): void {
    if (this.learningForm.valid) {
      const formValues = this.learningForm.value;
      this.learning = {
        ...formValues,
        url: formValues.urls.map((group: any) => group.url),
        steps: this.step

      };
      console.log(this.step);
      console.log(this.learning);
      this.learningService.saveLearning(this.learning).subscribe({
        next: (response) => {
          console.log('Learning saved:', response);
          this.router.navigate(['/add-doing'], { state: { step: this.step } });
          console.log(this.step);

        },
        error: (err) => {
          console.error('Error saving learning part:', err);
        }
      });
    }
  }
}
