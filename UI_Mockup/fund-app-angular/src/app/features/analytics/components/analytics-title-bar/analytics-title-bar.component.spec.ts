import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AnalyticsTitleBarComponent } from './analytics-title-bar.component';

describe('AnalyticsTitleBarComponent', () => {
  let component: AnalyticsTitleBarComponent;
  let fixture: ComponentFixture<AnalyticsTitleBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnalyticsTitleBarComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AnalyticsTitleBarComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
