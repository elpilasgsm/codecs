import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SanctionsConfigComponent } from './sanctions-config.component';

describe('SanctionsConfigComponent', () => {
  let component: SanctionsConfigComponent;
  let fixture: ComponentFixture<SanctionsConfigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SanctionsConfigComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SanctionsConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
