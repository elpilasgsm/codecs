import {Component, Input, OnInit} from '@angular/core';
import {MzBaseModal} from "ngx-materialize";
import {Changes} from "../changes";

@Component({
  selector: 'app-change-edit-dialog',
  templateUrl: './change-edit-dialog.component.html',
  styleUrls: ['./change-edit-dialog.component.css']
})
export class ChangeEditDialogComponent extends MzBaseModal {
  @Input() private change: Changes;

  constructor() {
    super();
  }

  ngOnInit() {
  }

}
