import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { StepsService } from '../../services/steps/steps.service';
import { Step } from '../../models/step';
import { Parcours } from '../../models/parcours';
import { Observable, of, startWith } from 'rxjs';
import { map } from 'rxjs/operators';
import { ParcoursService } from '../../services/parcours/parcours.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-step',
  templateUrl: './add-step.component.html',
  styleUrls: ['./add-step.component.css']
})
export class AddStepComponent implements OnInit {
  stepForm!: FormGroup;
  parcoursCtrl = new FormControl();
  filteredParcours!: Observable<Parcours[]>;
  allParcours: Parcours[] = [];
  par!: string;
  step!: Step;

  constructor(
    private fb: FormBuilder,
    private stepsService: StepsService,
    private parcoursService: ParcoursService,
    private router: Router
  ) {
    this.stepForm = this.fb.group({
      title: [''],
      description: [''],
      durationInMinutes: [''],
      parcours: [''],
      stepProcess: ['']
    });
  }

  ngOnInit(): void {
    this.step = {
      id: 1,
      title: '',
      description: '',
      durationInMinutes: 0,
      parcours: {
        id: 1,
        parcoursName: '',
        parcoursDescription: '',
      },
      stepProcess: '',
      startTime: "",
      endTime: "",
      status: false,
      completed: false
    };
    this.loadParcours();
    this.filteredParcours = this.parcoursCtrl.valueChanges.pipe(
      startWith(''),
      map(value => this._filterParcours(value))
    );
  }

  private _filterParcours(value: string): Parcours[] {
    const filterValue = value.toLowerCase();
    return this.allParcours.filter(parcours => parcours.parcoursName.toLowerCase().includes(filterValue));
  }

  loadParcours() {
    this.parcoursService.getAllParcours().subscribe(
      data => {
        this.allParcours = data;
      },
      error => {
        console.error('Erreur lors du chargement des parcours: ', error);
      }
    );
  }

  onParcoursSelected(parcours: Parcours) {
    this.par = parcours.parcoursName;
    this.step.parcours = parcours;
    this.parcoursCtrl.setValue(parcours.parcoursName, { emitEvent: false });
  }

  saveStep(): void {
    if (this.stepForm.valid) {
      this.step = this.stepForm.value;
      this.step.parcours = this.allParcours.find(p => p.parcoursName === this.par) || this.step.parcours;
      console.log(this.step);
      this.stepsService.saveStep(this.step).subscribe({
        next: (response) => {
          console.log('Step saved:', response);
          this.router.navigate(['/add-learning'], { state: { step: this.step } });
        },
        error: (err) => {
          console.error('Error saving step:', err);
        }
      });
    }
  }
}