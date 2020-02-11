import {Component, Input} from '@angular/core';
import {MzBaseModal} from "ngx-materialize";
import {SanctionChange} from "../sanction-change";
import {Sanction} from "../sanction";
import {SanctionsService} from "../sanctions.service";

@Component({
  selector: 'app-sanction-changes-edit-dialog',
  templateUrl: './sanction-changes-edit-dialog.component.html',
  styleUrls: ['./sanction-changes-edit-dialog.component.css']
})
export class SanctionChangesEditDialogComponent extends MzBaseModal {
  @Input() sanctionChangeInput: SanctionChange;
  private sanctionChange: SanctionChange;
  @Input() public sanctionsList: Sanction[];
  @Input() public primary: boolean;

  constructor(private  sanctionsService: SanctionsService) {
    super();
  }

  getSanctions() {
    return this.sanctionsList;
  }

  ngOnInit() {
    this.sanctionChange = Object.assign({}, this.sanctionChangeInput);
    this.sanctionChange.sanction = Object.assign({}, this.sanctionChangeInput.sanction);
    this.sanctionChange.change = Object.assign({}, this.sanctionChangeInput.change);
  }

  save() {
    if (!this.sanctionChange.id) {
      this.sanctionChange.alternate = !this.primary;
      this.sanctionsService.put(this.sanctionChange, function (s) {
        this.sanctionChange = Object.assign({}, s);
        this.sanctionChange.change = this.sanctionChangeInput.change;
      }.bind(this));
    }
  }

}
