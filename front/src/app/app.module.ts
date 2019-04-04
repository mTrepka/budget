import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavComponent } from './components/nav/nav.component';
import { FooterComponent } from './components/footer/footer.component';
import { StatisticsComponent } from './pages/statistics/statistics.component';
import { EventsComponent } from './pages/events/events.component';
import { EventComponent } from './pages/event/event.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { HomeComponent } from './pages/home/home.component';
import {AppRoutingModule} from './app-routing/app-routing.module';
import { YearChartComponent } from './components/year-chart/year-chart.component';
import { ChartsModule } from 'ng2-charts';
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
    AppRoutingModule,
    BrowserModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
