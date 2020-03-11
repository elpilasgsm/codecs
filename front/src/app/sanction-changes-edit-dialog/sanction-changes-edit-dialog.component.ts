import {Component, Input} from '@angular/core';
import {MzBaseModal} from "ngx-materialize";
import {SanctionChange} from "../sanction-change";
import {Sanction} from "../sanction";
import {SanctionsService} from "../sanctions.service";
import {Changes} from "../changes";

@Component({
  selector: 'app-sanction-changes-edit-dialog',
  templateUrl: './sanction-changes-edit-dialog.component.html',
  styleUrls: ['./sanction-changes-edit-dialog.component.css']
})
export class SanctionChangesEditDialogComponent extends MzBaseModal {
  @Input() change: Changes;

  @Input() public sanctionChange: SanctionChange;
  public editableChange: SanctionChange;
  @Input() public sanctionsList: Sanction[];
  @Input() public primary: boolean;

  constructor(private  sanctionsService: SanctionsService) {
    super();
  }

  getSanctions() {
    return this.sanctionsList;
  }

  ngOnInit() {
    this.editableChange = Object.assign({}, this.sanctionChange);
  }

  save() {
    this.sanctionChange.sanction = this.editableChange.sanction;
    this.sanctionChange.from = this.editableChange.from;
    this.sanctionChange.to = this.editableChange.to;
    if (!this.sanctionChange.change) {
      if (this.primary) {
        if (!this.change.primarySanctions) {
          this.change.primarySanctions = [];
        }
        this.change.primarySanctions.push(this.sanctionChange);
      } else {
        if (!this.change.alternateSanctions) {
          this.change.alternateSanctions = [];
        }
        this.change.alternateSanctions.push(this.sanctionChange);
      }
    }
  }

}
