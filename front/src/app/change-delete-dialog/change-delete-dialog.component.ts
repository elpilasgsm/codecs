import {Component, Input, OnInit} from '@angular/core';
import {Changes} from "../changes";
import {MzBaseModal} from "ngx-materialize";

@Component({
  selector: 'app-change-delete-dialog',
  templateUrl: './change-delete-dialog.component.html',
  styleUrls: ['./change-delete-dialog.component.css']
})
export class ChangeDeleteDialogComponent extends MzBaseModal {
  @Input() change: Changes;
  @Input() onAgree;

  constructor() {
    super();
  }


  ngOnInit() {
  }

}
