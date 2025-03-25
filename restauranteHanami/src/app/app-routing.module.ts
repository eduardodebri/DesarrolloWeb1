import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './paginas/landing-page/landing-page.component';

const routes: Routes = [

  { path: 'hanami', component: LandingPageComponent},
   { path: '', redirectTo: '/hanami', pathMatch: 'full' } 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
