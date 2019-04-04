import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { HomeComponent } from '../pages/home/home.component';
import {EventComponent} from '../pages/event/event.component';
import {EventsComponent} from '../pages/events/events.component';
import {SettingsComponent} from '../pages/settings/settings.component';
import {StatisticsComponent} from '../pages/statistics/statistics.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'event', component: EventComponent},
  {path: 'events', component: EventsComponent},
  {path: 'settings', component: SettingsComponent},
  {path: 'statistics', component: StatisticsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
