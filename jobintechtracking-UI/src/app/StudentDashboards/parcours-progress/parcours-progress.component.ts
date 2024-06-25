import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../../services/DataService/data-service.service';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Step } from '../../models/step';

interface Category {
  icon: string;
  name: string;
  parcoursCount: number;
  date: string;
}
interface Student {
  id: number;
  fullName: string;
}

@Component({
  selector: 'app-parcours-progress',
  templateUrl: './parcours-progress.component.html',
  styleUrls: ['./parcours-progress.component.css']
})
export class ParcoursProgressComponent implements OnInit {

  categories: Category[] = [
    {
      icon: 'assets/javaicon.png',
      name: 'JAVA',
      parcoursCount: 10,
      date: 'Apr 2, 2023'
    },
    // {
    //   icon: 'assets/angularicon.png',
    //   name: 'Angular',
    //   parcoursCount: 8,
    //   date: 'May 2, 2023'
    // },
  ];

  parcoursList: any[] = [];
  steps: Step[] = [];
  selectedParcours: number = 1;
  studentId: number = 1;

  students: Student[] = [
    {
      id: 1,
      fullName: 'hamza garrab'
    },
    {
      id: 2,
      fullName: 'zineb elmalki'
    },
    {
      id: 3,
      fullName: 'nadia outizi'
    }
  ];

  selectedStudent: Student | null = this.students[0]; // Default to the first student
  studentFormControl = new FormControl();
  filteredStudents: Observable<Student[]> | undefined;


  constructor(private dataService: DataService, private router: Router) {
    this.filteredStudents = this.studentFormControl.valueChanges.pipe(
      startWith(''),
      map(value => typeof value === 'string' ? value : value.fullName),
      map(name => name ? this._filterStudents(name) : this.students.slice())
    );
  }

  private _filterStudents(value: string): Student[] {
    const filterValue = value.toLowerCase();
    return this.students.filter(student => student.fullName.toLowerCase().includes(filterValue));
  }

  displayFn(student: Student): string {
    return student && student.fullName ? student.fullName : '';
  }

  onStudentSelectionChanged(event: any): void {
    const selectedFullName = event.option.value;
    this.selectedStudent = this.students.find(student => student.fullName === selectedFullName) ?? null;
    if (this.selectedStudent) {
      this.studentId = this.selectedStudent.id;
      this.loadSteps();

      // Navigate to the appropriate URL with the selected student id
      this.router.navigate(['/student-steps', 'steps-status', this.studentId, this.selectedParcours]);
    }
  }

  ngOnInit(): void {
    this.loadSteps();
  }

  loadSteps(): void {
    this.dataService.getStepsWithCompletionStatus(this.studentId, this.selectedParcours).subscribe(data => {
      this.steps = data.map(stepWithStatus => ({
        ...stepWithStatus,
        category: '',
        startTime: stepWithStatus.startTime, // Ensure startTime is included
        endTime: null
      }));
      console.log('Loaded steps:', this.steps);
    });
  }

  startStep(step: Step): void {
    this.dataService.startStep(this.studentId, step.id).subscribe(updatedStep => {
      step.startTime = updatedStep.startTime;
      step.completed = updatedStep.completed;
      console.log('Started step:', step);
      this.loadSteps();
    });
  }

  isStepAccessible(index: number): boolean | null {
    if (index === 0) {
      return true;
    }
    return this.steps[index - 1].completed;
  }

  isStepLocked(step: Step): boolean {
    const previousStepIndex = this.steps.findIndex(s => s.id === step.id) - 1;
    return previousStepIndex >= 0 && !this.steps[previousStepIndex].completed;
  }

  isStepStarted(step: Step): boolean {
    return step.startTime !== null && step.startTime !== undefined;
  }

  isStepLockedAfterFinishTime(step: Step): boolean {
    // Check if the step status is true, meaning it is finished and should be locked
    return step.status;
  }


  navigateToStep(step: Step): void {
    if (!this.isStepLocked(step) && !this.isStepLockedAfterFinishTime(step)) {
      this.startStep(step);
      this.router.navigate([`/learning-doing/${step.id}`]);
    }
  }
}