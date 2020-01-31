import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeEditComponent } from './change-edit.component';

describe('ChangeEditComponent', () => {
  let component: ChangeEditComponent;
  let fixture: ComponentFixture<ChangeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
