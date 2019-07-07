import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavComponent} from './components/nav/nav.component';
import {FooterComponent} from './components/footer/footer.component';
import {StatisticsComponent} from './pages/statistics/statistics.component';
import {EventsComponent} from './pages/events/events.component';
import {EventComponent} from './pages/event/event.component';
import {SettingsComponent} from './pages/settings/settings.component';
import {HomeComponent} from './pages/home/home.component';
import {AppRoutingModule} from './app-routing/app-routing.module';
import {YearChartComponent} from './components/year-chart/year-chart.component';
import {ChartsModule} from 'ng2-charts';
import {MatRadioModule} from '@angular/material/radio';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatDatepickerModule,
  MatFormFieldModule,
  MatInputModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatSelectModule,
  MatSortModule,
  MatTableModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MonthChartComponent} from './components/month-chart/month-chart.component';
import {CategoryChartComponent} from './components/category-chart/category-chart.component';
import {MonthPieChartComponent} from './components/month-circle-chart/month-pie-chart.component';
import {UserSettingsComponent} from './components/user-settings/user-settings.component';
import {CategoriesComponent} from './components/categories/categories.component';
import {CategoryComponent} from './components/category/category.component';
import {LoginComponent} from './pages/login/login.component';
import {JwtInterceptor} from './helpers/jwt.interceptor';
import {ErrorInterceptor} from './helpers/error.interceptor';
import {RegistrationComponent} from './pages/registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    FooterComponent,
    StatisticsComponent,
    EventsComponent,
    EventComponent,
    SettingsComponent,
    HomeComponent,
    YearChartComponent,
    MonthChartComponent,
    CategoryChartComponent,
    MonthPieChartComponent,
    UserSettingsComponent,
    CategoriesComponent,
    CategoryComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    MatRadioModule,
    AppRoutingModule,
    BrowserModule,
    ChartsModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatTableModule, MatPaginatorModule, MatSortModule, MatButtonToggleModule, MatDatepickerModule, MatNativeDateModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
