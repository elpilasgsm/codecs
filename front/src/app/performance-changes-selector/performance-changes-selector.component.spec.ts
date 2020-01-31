import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformanceChangesSelectorComponent } from './performance-changes-selector.component';

describe('PerformanceChangesSelectorComponent', () => {
  let component: PerformanceChangesSelectorComponent;
  let fixture: ComponentFixture<PerformanceChangesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PerformanceChangesSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PerformanceChangesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
