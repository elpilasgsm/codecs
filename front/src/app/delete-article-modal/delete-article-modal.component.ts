import {Component, Input, OnInit} from '@angular/core';
import {MzBaseModal, MzModalComponent} from 'ngx-materialize';

@Component({
  selector: 'app-delete-article-modal',
  templateUrl: './delete-article-modal.component.html',
  styleUrls: ['./delete-article-modal.component.css']
})
export class DeleteArticleModalComponent extends MzBaseModal {

  @Input() private onAgree;
  @Input() private article;
  private code = Math.floor(Math.random() * 89999) + 10000;
  private interedCode: number;
  public modalOptions: Materialize.ModalOptions = {
    dismissible: false, // Modal can be dismissed by clicking outside of the modal
    opacity: .5, // Opacity of modal background
    inDuration: 300, // Transition in duration
    outDuration: 200, // Transition out duration
    startingTop: '100%', // Starting top style attribute
    endingTop: '10%', // Ending top style attribute

    complete: () => {
      alert('Closed');
    } // Callback for Modal close
  };

  constructor() {
    super();
  }



  ngOnInit() {

  }

}
