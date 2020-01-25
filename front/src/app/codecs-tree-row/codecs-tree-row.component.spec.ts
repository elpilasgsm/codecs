import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CodecsTreeRowComponent } from './codecs-tree-row.component';

describe('CodecsTreeRowComponent', () => {
  let component: CodecsTreeRowComponent;
  let fixture: ComponentFixture<CodecsTreeRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CodecsTreeRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CodecsTreeRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
