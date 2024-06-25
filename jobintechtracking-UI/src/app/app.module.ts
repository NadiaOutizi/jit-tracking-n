import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { HeaderComponent } from './layouts/header/header.component';
import { EditStepComponent } from './ExpertDashboards/edit-step/edit-step.component';
import { AddStepComponent } from './ExpertDashboards/add-step/add-step.component';
import { ParcoursProgressComponent } from './StudentDashboards/parcours-progress/parcours-progress.component';
import { StudentProfileComponent } from './StudentDashboards/student-profile/student-profile.component';
import { LearningDoingComponent } from './StudentDashboards/learning-doing/learning-doing.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input'; 
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { ParcoursComponent } from './ExpertDashboards/parcours/parcours.component';
import { AddLearningComponent } from './ExpertDashboards/add-learning/add-learning.component';
import { AddDoingComponent } from './ExpertDashboards/add-doing/add-doing.component';
import { StepsComponent } from './ExpertDashboards/steps-card/steps.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HeaderComponent,
    EditStepComponent,
    AddStepComponent,
    ParcoursProgressComponent,
    StudentProfileComponent,
    LearningDoingComponent,
    ParcoursComponent,
    AddLearningComponent,
    AddDoingComponent,
    StepsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatInputModule,
    MatFormFieldModule
  ],
  providers: [provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule { }
