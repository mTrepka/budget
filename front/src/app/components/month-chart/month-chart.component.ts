import {Component, OnInit, ViewChild} from '@angular/core';
import {ChartDataSets, ChartOptions} from 'chart.js';
import {BaseChartDirective, Label} from 'ng2-charts';
import * as pluginAnnotations from 'chartjs-plugin-annotation';
import {EventService} from '../../service/event.service';
import {range} from 'rxjs';

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
    const a = new Date();
    const today = a.getDate();
    let wyd = [];
    let prz = [];

    const startDate = a.getFullYear() + '-' + (a.getMonth() + 1) + '-' + 1;
    const endDate = a.getFullYear() + '-' + (a.getMonth() + 1) + '-' + (a.getDate() + 1);
    this.eventService.getEventsByDate(startDate, endDate).subscribe(e =>
      e.forEach(b => {
        // @ts-ignore
        const d = new Date(b.eventDate);
        if (b.type === 'Expenses') {
          if (wyd[d.getDate() - 1] === undefined) {
            // @ts-ignore
            wyd[d.getDate() - 1] = b.value;
          } else {
            // @ts-ignore
            wyd[d.getDate() - 1] += b.value;
          }
        } else {
          // @ts-ignore
          if (prz[d.getDate() - 1] === undefined) {
            // @ts-ignore
            prz[d.getDate() - 1] = b.value;
          } else {
            // @ts-ignore
            prz[d.getDate() - 1] += b.value;
          }
        }
      })
    );

    range(1, today + 1).subscribe(b => this.lineChartLabels.push(b + ''));
    this.lineChartData = [
      {data: wyd, label: 'Expenses'},
      {data: prz, label: 'Revenues'},
    ];
  }

  // events
  public chartClicked({event, active}: { event: MouseEvent, active: {}[] }): void {
  }

  public chartHovered({event, active}: { event: MouseEvent, active: {}[] }): void {
  }
}
