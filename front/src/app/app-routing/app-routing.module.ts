import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from '../pages/home/home.component';
import {EventComponent} from '../pages/event/event.component';
import {EventsComponent} from '../pages/events/events.component';
import {SettingsComponent} from '../pages/settings/settings.component';
import {StatisticsComponent} from '../pages/statistics/statistics.component';
import {UserSettingsComponent} from '../components/user-settings/user-settings.component';
import {CategoriesComponent} from '../components/categories/categories.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'event', component: EventComponent},
  {path: 'events', component: EventsComponent},
  {path: 'statistics', component: StatisticsComponent},
  {
    path: 'settings', component: SettingsComponent,
    children: [
      {
        path: '',
        component: UserSettingsComponent,
      },
      {
        path: 'user',
        component: UserSettingsComponent,
      },
      {
        path: 'categories',
        component: CategoriesComponent,
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
