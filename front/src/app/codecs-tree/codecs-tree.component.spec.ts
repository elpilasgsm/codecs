import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CodecsTreeComponent } from './codecs-tree.component';

describe('CodecsTreeComponent', () => {
  let component: CodecsTreeComponent;
  let fixture: ComponentFixture<CodecsTreeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CodecsTreeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CodecsTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
