import {Component, Input, OnInit} from '@angular/core';
import {Changes} from "../changes";
import {SanctionsService} from "../sanctions.service";
import {Sanction} from "../sanction";
import {SanctionChange} from "../sanction-change";

@Component({
  selector: 'app-sanction-changes-selector',
  templateUrl: './sanction-changes-selector.component.html',
  styleUrls: ['./sanction-changes-selector.component.css']
})
export class SanctionChangesSelectorComponent implements OnInit {
  @Input() change: Changes;
  @Input() primary: boolean;
  private sanctionsList: Sanction[];
  private title: string;

  constructor(private sanctionService: SanctionsService) {
  }

  getAllSanctions(): Sanction[] {
    return this.sanctionsList;
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

}
