import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Step } from '../../models/step';
import { Doing } from '../../models/doing';
import { Router } from '@angular/router';
import { DoingService } from '../../services/doing/doing.service';

@Component({
  selector: 'app-add-doing',
  templateUrl: './add-doing.component.html',
  styleUrl: './add-doing.component.css'
})
export class AddDoingComponent implements OnInit {
  doingForm!: FormGroup;
  step!: Step;
  doing!: Doing;

  constructor(private router: Router,
    private doingService: DoingService,
    private fb: FormBuilder) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state) {
      this.step = navigation.extras.state['step'];
    }
    this.doingForm = this.fb.group({
      task: [''],
    });
  }

  ngOnInit(): void {
    this.doing = {
      id: 1,
      task: '',
      steps: this.step
    }
    console.log(this.step);
  }

  saveDoing(): void {
    if (this.doingForm.valid) {
      this.doing = this.doingForm.value;
      this.doing.steps = this.step;
      console.log(this.doing);
      this.doingService.saveDoing(this.doing).subscribe({
        next: (response) => {
          console.log('Doing saved:', response);
          alert("doing part is saved")
          this.router.navigate(['/parcours']);
        },
        error: (err) => {
          console.error('Error saving doing part:', err);
        }
      });
    }
  }

}
