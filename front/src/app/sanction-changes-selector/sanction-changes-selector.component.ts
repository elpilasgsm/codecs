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
      change: this.change,
      optional: false,
      alternate: !this.primary
    };
  }

  edit(ch: SanctionChange) {
    ch.change = this.change;
    this.modalService.open(SanctionChangesEditDialogComponent, {
      sanctionChangeInput: ch,
      sanctionsList: this.sanctionsList,
      primary: this.primary
      /*      onAgree: function () {
              this.articleServiceService.deleteChangeById(ch.id).subscribe(a => {
                if (200 === a) {
                  this.articleServiceService.getRoot(function (tree: Article[]) {
                    this.toastService.show(`Изменение ${ch.name} успешно удалено!`,
                      4000,
                      'green');
                    this.ngOnChanges();
                  }.bind(this));
                }
              });
            }.bind(this)*/
    });
  }

  add() {
    const dialogRef = this.modalService.open(SanctionChangesEditDialogComponent, {
      sanctionChangeInput: this.newInstance(),
      sanctionsList: this.sanctionsList,
      primary: this.primary
      /*      onAgree: function () {
              this.articleServiceService.deleteChangeById(ch.id).subscribe(a => {
                if (200 === a) {
                  this.articleServiceService.getRoot(function (tree: Article[]) {
                    this.toastService.show(`Изменение ${ch.name} успешно удалено!`,
                      4000,
                      'green');
                    this.ngOnChanges();
                  }.bind(this));
                }
              });
            }.bind(this)*/
    });

    dialogRef.onDestroy(function () {
      console.log("Dialog is closed.")
    });
  }
}
