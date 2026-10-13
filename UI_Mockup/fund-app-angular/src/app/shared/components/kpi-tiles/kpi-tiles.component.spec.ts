import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KpiTilesComponent } from './kpi-tiles.component';

describe('KpiTilesComponent', () => {
  let component: KpiTilesComponent;
  let fixture: ComponentFixture<KpiTilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KpiTilesComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(KpiTilesComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
