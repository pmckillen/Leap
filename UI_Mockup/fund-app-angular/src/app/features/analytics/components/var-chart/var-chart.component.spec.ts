import { ComponentFixture, TestBed } from '@angular/core/testing';
import { VarChartComponent } from './var-chart.component';

describe('VarChartComponent', () => {
  let component: VarChartComponent;
  let fixture: ComponentFixture<VarChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VarChartComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(VarChartComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
