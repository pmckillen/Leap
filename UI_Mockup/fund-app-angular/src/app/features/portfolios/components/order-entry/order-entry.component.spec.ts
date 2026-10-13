import { ComponentFixture, TestBed } from '@angular/core/testing';
import { OrderEntryComponent } from './order-entry.component';

describe('OrderEntryComponent', () => {
  let component: OrderEntryComponent;
  let fixture: ComponentFixture<OrderEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderEntryComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(OrderEntryComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
