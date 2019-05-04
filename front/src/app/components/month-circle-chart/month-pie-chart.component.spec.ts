import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MonthPieChartComponent} from './month-pie-chart.component';

describe('MonthPieChartComponent', () => {
  let component: MonthPieChartComponent;
  let fixture: ComponentFixture<MonthPieChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MonthPieChartComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthPieChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
