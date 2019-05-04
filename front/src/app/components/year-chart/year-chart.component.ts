import {Component, OnInit, ViewChild} from '@angular/core';
import {ChartDataSets, ChartOptions} from 'chart.js';
import {BaseChartDirective, Label} from 'ng2-charts';
import * as pluginAnnotations from 'chartjs-plugin-annotation';
import {EventService} from '../../service/event.service';
import {Event} from '../Event';

@Component({
  selector: 'app-year-chart',
  templateUrl: './year-chart.component.html',
  styleUrls: ['./year-chart.component.scss']
})
export class YearChartComponent implements OnInit {
  events = Event[''];
  months = ['Styczeń', 'Luty', 'Marzec', 'Kwiecień', 'Maj', 'Czerwiec', 'Lipiec', 'Sierpień', 'Wrzesien', 'Pazdziernik', 'Listopad', 'Grudzien'];
  public lineChartData: ChartDataSets[];
  public lineChartLabels: Label[] = this.months;
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
    annotation: {
    },
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
    const wyd = [];
    const prz = [];
    wyd[0] = 0;
    const startDate = a.getFullYear() + '-' + 1 + '-' + 1;
    const endDate = (a.getFullYear()) + '-' + 12 + '-' + 31;

    this.eventService.getEventsByDate(startDate, endDate).subscribe(e =>
      e.forEach(b => {
        const d = new Date(b.eventDate);
        if (prz[d.getMonth()] === undefined) {
          prz[d.getMonth()] = 0;
        }
        if (wyd[d.getMonth()] === undefined) {
          wyd[d.getMonth()] = 0;
        }
        if (b.type === 'wyd') {
          wyd[d.getMonth()] += b.value;
        } else {
          prz[d.getMonth()] += b.value;
        }
      })
    );
    this.lineChartData = [
      {data: wyd, label: 'Wydatki'},
      {data: prz, label: 'Przychody'},
    ];
  }

  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
  }
}
