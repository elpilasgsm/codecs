import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrimeSeverityViewComponent } from './crime-severity-view.component';

describe('CrimeSeverityViewComponent', () => {
  let component: CrimeSeverityViewComponent;
  let fixture: ComponentFixture<CrimeSeverityViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrimeSeverityViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrimeSeverityViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
