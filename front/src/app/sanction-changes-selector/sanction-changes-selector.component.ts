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
  private sanctionsList: Sanction[];

  constructor(private sanctionService: SanctionsService,
              private modalService: MzModalService,
              private toastService: MzToastService) {
  }

  getTitle() {
    return "Основное наказание";
  }

  getSanctionsChanges(): SanctionChange[] {
    return this.change.primarySanctions;
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
      alternateSanctions: [],
      mainSanction: null
    }
      ;
  }

  delete(ch: SanctionChange) {
    this.change.primarySanctions = this.change.primarySanctions.filter(item => item !== ch);

  }

  edit(ch: SanctionChange) {
    this.modalService.open(SanctionChangesEditDialogComponent, {
      change: this.change,
      sanctionsList: this.sanctionsList,
      sanctionChange: ch,
      primary: true
    });
  }

  add() {
    const dialogRef = this.modalService.open(SanctionChangesEditDialogComponent, {
      change: this.change,
      sanctionsList: this.sanctionsList,
      sanctionChange: this.newInstance(),
      primary: true
    });

    dialogRef.onDestroy(function () {
      console.log("Dialog is closed.")
    });
  }
}
