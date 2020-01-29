import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MethodChangesSelectorComponent } from './method-changes-selector.component';

describe('MethodChangesSelectorComponent', () => {
  let component: MethodChangesSelectorComponent;
  let fixture: ComponentFixture<MethodChangesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MethodChangesSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MethodChangesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
