import {Component, OnInit, ViewChild} from '@angular/core';
import {ChartDataSets, ChartOptions} from 'chart.js';
import {BaseChartDirective, Label} from 'ng2-charts';
import * as pluginAnnotations from 'chartjs-plugin-annotation';
import {EventService} from '../../service/event.service';
import {range} from 'rxjs';
import {Event} from '../Event';

@Component({
  selector: 'app-month-chart',
  templateUrl: './month-chart.component.html',
  styleUrls: ['./month-chart.component.scss']
})
export class MonthChartComponent implements OnInit {
  events = Event[''];
  public lineChartData: ChartDataSets[];
  public lineChartLabels: Label[] = [];
  public lineChartOptions: (ChartOptions & { annotation: any }) = {
    responsive: true,
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [{}],
      yAxes: [
        {
          id: 'y-axis-0',
          position: 'left',
        }
      ]
    },
    annotation: {},
  };
  public lineChartLegend = true;
  public lineChartType = 'bar';
  public lineChartPlugins = [pluginAnnotations];

  @ViewChild(BaseChartDirective) chart: BaseChartDirective;

  constructor(private eventService: EventService) {
  }

  ngOnInit() {

    let a = new Date();
    const today = a.getDate();
    const wyd = [];
    const prz = [];
    wyd[0] = 0;
    const startDate = a.getFullYear() + '-' + (a.getMonth() + 1) + '-' + 1;
    const endDate = a.getFullYear() + '-' + (a.getMonth() + 1) + '-' + (a.getDate() + 1);
    console.log(endDate);
    this.eventService.getEventsByDate(startDate, endDate).subscribe(e =>
      e.forEach(b => {
        const d = new Date(b.eventDate);
        console.log(e);
        if (prz[d.getDate()] === undefined) {
          prz[d.getDate()] = 0;
        }
        if (wyd[d.getDate()] === undefined) {
          wyd[d.getDate()] = 0;
        }
        if (b.type === 'wyd') {
          wyd[d.getDate()] = b.value;
        } else {
          prz[d.getDate()] = b.value;
        }
      })
    );

    range(0, today + 1).subscribe(b => this.lineChartLabels.push(b + ''));
    this.lineChartData = [
      {data: wyd, label: 'Wydatki'},
      {data: prz, label: 'Przychody'},
    ];
  }

  // events
  public chartClicked({event, active}: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({event, active}: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }
}
