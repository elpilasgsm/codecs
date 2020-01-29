import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectionChangesSelectorComponent } from './direction-changes-selector.component';

describe('DirectionChangesSelectorComponent', () => {
  let component: DirectionChangesSelectorComponent;
  let fixture: ComponentFixture<DirectionChangesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DirectionChangesSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectionChangesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
