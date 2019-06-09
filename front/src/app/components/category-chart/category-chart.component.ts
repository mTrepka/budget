import {Component, OnInit} from '@angular/core';
import {ChartOptions, ChartType} from 'chart.js';
import {Label} from 'ng2-charts';
import {CategoryService} from '../../service/category.service';

@Component({
  selector: 'app-category-chart',
  templateUrl: './category-chart.component.html',
  styleUrls: ['./category-chart.component.scss']
})
export class CategoryChartComponent implements OnInit {

  public radarChartOptions: ChartOptions = {
    responsive: true,
  };
  public radarChartLabels: Label[] = [''];

  public radarChartData;
  public radarChartType: ChartType = 'radar';

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit() {
    const wyd = [];
    const prz = [];
    const labels = [];
    let i = 0;
    this.categoryService.getCategories().subscribe(e => e.forEach(b => {
      labels[i] = b.name;
      wyd[i] = 0;
      prz[i] = 0;
      b.eventList.forEach(c => {
        if (c.type === 'wyd') {
          wyd[i] = c.value;
        } else {
          prz[i] = c.value;
        }
      });
      i++;
    }));
    this.radarChartData = [
      {data: wyd, label: 'Expenses'},
      {data: prz, label: 'Revenues'},
    ];
    this.radarChartLabels = labels;
  }
}
