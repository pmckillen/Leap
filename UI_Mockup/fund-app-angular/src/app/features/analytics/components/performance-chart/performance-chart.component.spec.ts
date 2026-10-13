import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PerformanceChartComponent } from './performance-chart.component';

describe('PerformanceChartComponent', () => {
  let component: PerformanceChartComponent;
  let fixture: ComponentFixture<PerformanceChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerformanceChartComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PerformanceChartComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
