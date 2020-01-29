import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InPartChangesSelectorComponent } from './in-part-changes-selector.component';

describe('InPartChangesSelectorComponent', () => {
  let component: InPartChangesSelectorComponent;
  let fixture: ComponentFixture<InPartChangesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InPartChangesSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InPartChangesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
