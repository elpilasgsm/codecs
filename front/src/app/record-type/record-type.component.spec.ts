import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordTypeComponent } from './record-type.component';

describe('RecordTypeComponent', () => {
  let component: RecordTypeComponent;
  let fixture: ComponentFixture<RecordTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecordTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
