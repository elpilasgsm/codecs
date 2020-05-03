import {Component, Input, ViewChild} from '@angular/core';
import {MzBaseModal, MzModalComponent, MzToastService} from "ngx-materialize";
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
  @ViewChild('saveDialog') modal: MzModalComponent;
  @Input() public sanctionChange: SanctionChange;
  @Input() public primary: boolean;
  @Input() public mainChange: SanctionChange;

  public editableChange: SanctionChange;
  @Input() public sanctionsList: Sanction[];

  constructor(private  sanctionsService: SanctionsService, private toastService: MzToastService) {
    super();
  }

  getSanctions() {
    return this.sanctionsList;
  }

  compareById(idFist: Sanction, idSecond: Sanction) {
    return idFist && idSecond && idFist.id == idSecond.id;
  }

  ngOnInit() {
    this.editableChange = Object.assign({}, this.sanctionChange);
  }

  save() {
    if (!this.editableChange.sanction || !this.editableChange.sanction.id) {
      this.toastService.show("Выберите санкцию", 2000, 'red');
      return;
    }
    this.sanctionChange.sanction = this.editableChange.sanction;
    this.sanctionChange.from = this.editableChange.from;
    this.sanctionChange.to = this.editableChange.to;
    this.sanctionChange.optional = this.editableChange.optional;
    if (!this.sanctionChange.id) {
      if (this.primary) {
        if (!this.change.primarySanctions) {
          this.change.primarySanctions = [];
        }
        this.change.primarySanctions.push(this.sanctionChange);
      } else {
        if (!this.mainChange.alternateSanctions) {
          this.mainChange.alternateSanctions = [];
        }
        this.mainChange.alternateSanctions.push(this.sanctionChange);
      }
    }
    this.modal.closeModal();
  }

}
