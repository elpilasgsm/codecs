import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrimeSereritySelectorComponent } from './crime-sererity-selector.component';

describe('CrimeSereritySelectorComponent', () => {
  let component: CrimeSereritySelectorComponent;
  let fixture: ComponentFixture<CrimeSereritySelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrimeSereritySelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrimeSereritySelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
