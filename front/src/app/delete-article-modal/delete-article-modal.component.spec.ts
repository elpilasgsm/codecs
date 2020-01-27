import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteArticleModalComponent } from './delete-article-modal.component';

describe('DeleteArticleModalComponent', () => {
  let component: DeleteArticleModalComponent;
  let fixture: ComponentFixture<DeleteArticleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteArticleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteArticleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
