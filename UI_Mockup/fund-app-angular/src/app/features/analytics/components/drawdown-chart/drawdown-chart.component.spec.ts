import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DrawdownChartComponent } from './drawdown-chart.component';

describe('DrawdownChartComponent', () => {
  let component: DrawdownChartComponent;
  let fixture: ComponentFixture<DrawdownChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DrawdownChartComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DrawdownChartComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
