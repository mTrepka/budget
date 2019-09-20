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
import {RegistrationComponent} from '../pages/registration/registration.component';
import {SecurityLogComponent} from '../components/security-log/security-log.component';
import {SocialLoginComponent} from '../pages/social-login/social-login.component';
import {ForgotpasswordComponent} from '../pages/forgotpassword/forgotpassword.component';
import {NewPasswordComponent} from '../pages/new-password/new-password.component';

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'event', component: EventComponent, canActivate: [AuthGuard]},
  {path: 'events', component: EventsComponent, canActivate: [AuthGuard]},
  {path: 'statistics', component: StatisticsComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'social-login', component: SocialLoginComponent},
  {path: 'forgot-password', component: ForgotpasswordComponent},
  {path: 'new-password', component: NewPasswordComponent},
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
      },
      {
        path: 'security',
        component: SecurityLogComponent,
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
