import {Component, Input, ViewChild} from '@angular/core';
import {Changes} from "../changes";
import {MzBaseModal, MzModalComponent} from "ngx-materialize";

@Component({
  selector: 'app-change-delete-dialog',
  templateUrl: './change-delete-dialog.component.html',
  styleUrls: ['./change-delete-dialog.component.css']
})
export class ChangeDeleteDialogComponent extends MzBaseModal {
  @Input() change: Changes;
  @Input() onAgree;
  @ViewChild('changeEditDialog')
  changeEditDialog: MzModalComponent;

  constructor() {
    super();
  }

  close() {
    this.changeEditDialog.closeModal();
  }

  ngOnInit() {
  }

}
