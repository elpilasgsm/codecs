import {Component, Input} from '@angular/core';
import {MzBaseModal} from "ngx-materialize";
import {Changes} from "../changes";

@Component({
  selector: 'app-change-edit-dialog',
  templateUrl: './change-edit-dialog.component.html',
  styleUrls: ['./change-edit-dialog.component.css']
})
export class ChangeEditDialogComponent extends MzBaseModal {
  @Input() change: Changes;
  @Input() private onSave;
  editableChange: Changes;
  public datepickerOptions: Pickadate.DateOptions = {
    clear: 'Очистить', // Clear button text
    close: 'Ok',    // Ok button text
    today: 'Сегодня', // Today button text
    onRender: function (event) {
      this.set(null);
    },
    closeOnClear: true,
    closeOnSelect: false,
    format: 'dd/mm/yyyy', // Visible date format (defaulted to formatSubmit if provided otherwise 'd mmmm, yyyy')
    formatSubmit: 'dd/mm/yyyy',   // Return value format (used to set/get value)
    selectMonths: true, // Creates a dropdown to control month
    min: [1995, 1, 1],
    max: [2030, 1, 1],
    selectYears: 50
  };

  constructor() {
    super();
  }

  ngOnChanges() {
    this.ngOnInit();
  }

  ngOnInit() {
    this.editableChange =
      Object.assign({}, this.change);
  }

  save() {
    this.change =
      Object.assign({}, this.editableChange);
    this.onSave(this.change);
  }

  cancel() {
    this.editableChange =
      Object.assign({}, this.change);
  }

}
