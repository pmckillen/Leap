import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PortfolioListComponent } from './portfolio-list.component';

describe('PortfolioListComponent', () => {
  let component: PortfolioListComponent;
  let fixture: ComponentFixture<PortfolioListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PortfolioListComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PortfolioListComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
