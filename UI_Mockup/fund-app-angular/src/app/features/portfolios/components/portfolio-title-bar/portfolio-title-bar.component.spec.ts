import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PortfolioTitleBarComponent } from './portfolio-title-bar.component';

describe('PortfolioTitleBarComponent', () => {
  let component: PortfolioTitleBarComponent;
  let fixture: ComponentFixture<PortfolioTitleBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PortfolioTitleBarComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PortfolioTitleBarComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
