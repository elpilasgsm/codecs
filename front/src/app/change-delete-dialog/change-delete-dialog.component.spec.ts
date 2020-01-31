import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeDeleteDialogComponent } from './change-delete-dialog.component';

describe('ChangeDeleteDialogComponent', () => {
  let component: ChangeDeleteDialogComponent;
  let fixture: ComponentFixture<ChangeDeleteDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeDeleteDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeDeleteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
