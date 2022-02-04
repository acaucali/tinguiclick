import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisputasComponent } from './disputas.component';

describe('DisputasComponent', () => {
  let component: DisputasComponent;
  let fixture: ComponentFixture<DisputasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisputasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisputasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
