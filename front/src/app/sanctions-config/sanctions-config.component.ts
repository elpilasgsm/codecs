import {Component, OnInit} from '@angular/core';
import {SanctionsService} from "../sanctions.service";
import {Sanction} from "../sanction";

@Component({
  selector: 'app-sanctions-config',
  templateUrl: './sanctions-config.component.html',
  styleUrls: ['./sanctions-config.component.css']
})
export class SanctionsConfigComponent implements OnInit {
  public sanctions: Sanction[];

  constructor(private sanctionsService: SanctionsService) {
  }

  getSanctions(): Sanction[] {
    return this.sanctions;
  }

  ngOnInit() {
    this.sanctionsService.getAll(function (sanc) {
      if (!this.sanctions) {
        this.sanctions = [];
      }
      this.sanctions.length = 0;
      this.sanctions.push.apply(this.sanctions, sanc);
    }.bind(this));
  }
}
