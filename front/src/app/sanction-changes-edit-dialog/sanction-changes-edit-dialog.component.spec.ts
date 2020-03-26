import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SanctionChangesEditDialogComponent} from './sanction-changes-edit-dialog.component';

describe('SanctionChangesEditDialogComponent', () => {
  let component: SanctionChangesEditDialogComponent;
  let fixture: ComponentFixture<SanctionChangesEditDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SanctionChangesEditDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SanctionChangesEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
