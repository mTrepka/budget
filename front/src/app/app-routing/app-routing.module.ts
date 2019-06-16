import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from '../pages/home/home.component';
import {EventComponent} from '../pages/event/event.component';
import {EventsComponent} from '../pages/events/events.component';
import {SettingsComponent} from '../pages/settings/settings.component';
import {StatisticsComponent} from '../pages/statistics/statistics.component';
import {UserSettingsComponent} from '../components/user-settings/user-settings.component';
import {CategoriesComponent} from '../components/categories/categories.component';
import {LoginComponent} from '../pages/login/login.component';
import {AuthGuard} from '../guards/auth.guard';

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'event', component: EventComponent, canActivate: [AuthGuard]},
  {path: 'events', component: EventsComponent, canActivate: [AuthGuard]},
  {path: 'statistics', component: StatisticsComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'settings', component: SettingsComponent, canActivate: [AuthGuard],
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
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
