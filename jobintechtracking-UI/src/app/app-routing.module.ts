import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EditStepComponent } from './ExpertDashboards/edit-step/edit-step.component';
import { AddStepComponent } from './ExpertDashboards/add-step/add-step.component';
import { ParcoursProgressComponent } from './StudentDashboards/parcours-progress/parcours-progress.component';
import { StudentProfileComponent } from './StudentDashboards/student-profile/student-profile.component';
import { LearningDoingComponent } from './StudentDashboards/learning-doing/learning-doing.component';
import { ParcoursComponent } from './ExpertDashboards/parcours/parcours.component';
import { AddLearningComponent } from './ExpertDashboards/add-learning/add-learning.component';
import { AddDoingComponent } from './ExpertDashboards/add-doing/add-doing.component';
import { StepsComponent } from './ExpertDashboards/steps-card/steps.component';

const routes: Routes = [

  { path: 'steps/parcours/:parcoursId', component: StepsComponent },
  { path: 'edit-step', component: EditStepComponent },
  { path: 'add-step', component: AddStepComponent },
  
  { path: 'step-progress' , component:ParcoursProgressComponent},
  { path: 'student-profile', component: StudentProfileComponent },
  { path: 'learning-doing/:id', component: LearningDoingComponent },
  { path: '', redirectTo: '/parcours', pathMatch: 'full' },

  { path: 'parcours', component: ParcoursComponent },
  { path: 'add-learning', component: AddLearningComponent},
  { path: 'add-doing', component: AddDoingComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
