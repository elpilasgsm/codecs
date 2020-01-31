import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeEditDialogComponent } from './change-edit-dialog.component';

describe('ChangeEditDialogComponent', () => {
  let component: ChangeEditDialogComponent;
  let fixture: ComponentFixture<ChangeEditDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeEditDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
