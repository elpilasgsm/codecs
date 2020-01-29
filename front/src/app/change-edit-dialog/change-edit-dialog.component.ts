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
  @Input() private onSave;
  public datepickerOptions: Pickadate.DateOptions = {
    clear: 'Очистить', // Clear button text
    close: 'Ok',    // Ok button text
    today: 'Сегодня', // Today button text
    closeOnClear: true,
    closeOnSelect: false,
    format: 'dd/mm/yyyy', // Visible date format (defaulted to formatSubmit if provided otherwise 'd mmmm, yyyy')
    formatSubmit: 'dd/mm/yyyy',   // Return value format (used to set/get value)
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 30,    // Creates a dropdown of 10 years to control year,
  };

  constructor() {
    super();
  }

  ngOnInit() {
  }

}
