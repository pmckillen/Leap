import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PnlByAssetClassComponent } from './pnl-by-asset-class.component';

describe('PnlByAssetClassComponent', () => {
  let component: PnlByAssetClassComponent;
  let fixture: ComponentFixture<PnlByAssetClassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PnlByAssetClassComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PnlByAssetClassComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
