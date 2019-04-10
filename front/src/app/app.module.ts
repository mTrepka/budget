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
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatTableModule,
  MatPaginatorModule,
  MatSortModule, MatButtonToggleModule,
  MatDatepickerModule, MatNativeDateModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';

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
    YearChartComponent
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}