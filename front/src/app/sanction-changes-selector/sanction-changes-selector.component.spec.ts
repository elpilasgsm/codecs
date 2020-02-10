import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SanctionChangesSelectorComponent} from './sanction-changes-selector.component';

describe('SanctionChangesSelectorComponent', () => {
  let component: SanctionChangesSelectorComponent;
  let fixture: ComponentFixture<SanctionChangesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SanctionChangesSelectorComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SanctionChangesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
