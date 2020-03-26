import {Component, Input, OnInit} from '@angular/core';
import {Changes} from "../changes";
import {SanctionsService} from "../sanctions.service";
import {Sanction} from "../sanction";
import {SanctionChange} from "../sanction-change";
import {MzModalService, MzToastService} from "ngx-materialize";
import {SanctionChangesEditDialogComponent} from "../sanction-changes-edit-dialog/sanction-changes-edit-dialog.component";

@Component({
  selector: 'app-sanction-changes-selector',
  templateUrl: './sanction-changes-selector.component.html',
  styleUrls: ['./sanction-changes-selector.component.css']
})
export class SanctionChangesSelectorComponent implements OnInit {
  @Input() change: Changes;
  @Input() primary: boolean;
  private sanctionsList: Sanction[];

  constructor(private sanctionService: SanctionsService,
              private modalService: MzModalService,
              private toastService: MzToastService) {
  }

  getTitle() {
    return `${this.primary ? "Основное наказание" : "Дополнительное наказание"}`;
  }

  getSanctionsChanges(): SanctionChange[] {
    if (this.primary) {
      return this.change.primarySanctions;
    } else {
      return this.change.alternateSanctions;
    }
  }

  ngOnInit() {
    this.sanctionService.getAll(function (s) {
      this.sanctionsList = s;
    }.bind(this));
  }

  newInstance(): SanctionChange {
    return {
      id: null
      , from: null, to: null, sanction: {id: null, name: null, metric: null},
      change: null,
      optional: false,
      alternate: !this.primary
    };
  }

  delete(ch: SanctionChange) {
    this.change.alternateSanctions= this.change.alternateSanctions.filter(item => item !== ch);
    this.change.primarySanctions= this.change.primarySanctions.filter(item => item !== ch);

  }

  edit(ch: SanctionChange) {
    this.modalService.open(SanctionChangesEditDialogComponent, {
      change: this.change,
      sanctionsList: this.sanctionsList,
      sanctionChange: ch,
      primary: this.primary
    });
  }

  add() {
    const dialogRef = this.modalService.open(SanctionChangesEditDialogComponent, {
      change: this.change,
      sanctionsList: this.sanctionsList,
      sanctionChange: this.newInstance(),
      primary: this.primary
    });

    dialogRef.onDestroy(function () {
      console.log("Dialog is closed.")
    });
  }
}
