import {Component, OnInit} from '@angular/core';
import {ChartOptions, ChartType} from 'chart.js';
import {Label} from 'ng2-charts';
import {EventService} from '../../service/event.service';

@Component({
  selector: 'app-month-circle-chart',
  templateUrl: './month-pie-chart.component.html',
  styleUrls: ['./month-pie-chart.component.scss']
})
export class MonthPieChartComponent implements OnInit {
  public pieChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      position: 'top',
    },
    plugins: {
      datalabels: {
        formatter: (value, ctx) => {
          const label = ctx.chart.data.labels[ctx.dataIndex];
          return label;
        },
      },
    }
  };
  public pieChartLabels: Label[] = ['Wydatki', 'Przychody'];
  public pieChartData: number[];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartColors = [
    {
      backgroundColor: ['rgba(255,0,0,0.3)', 'rgba(0,255,0,0.3)', 'rgba(0,0,255,0.3)'],
    },
  ];

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    this.pieChartData = [0, 0];
    this.applyNewData();
  }

  private applyNewData() {
    const a = new Date();
    const wyd = [];
    const prz = [];
    let i = 1;
    let j = 1;
    const startDate = a.getFullYear() + '-' + (a.getMonth() + 1) + '-' + 1;
    const endDate = a.getFullYear() + '-' + (a.getMonth() + 1) + '-' + (a.getDate() + 1);
    this.eventService.getEventsByDate(startDate, endDate).subscribe(e =>
      e.forEach(b => {
          if (b.type === 'wyd') {
            this.pieChartData[0] += b.value;
            i++;
          } else {
            this.pieChartData[1] = b.value;
            j++;
          }
        }
      ));
  }

  public chartClicked({event, active}: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({event, active}: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }
}
