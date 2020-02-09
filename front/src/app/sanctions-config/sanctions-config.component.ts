import {Component, OnInit} from '@angular/core';
import {SanctionsService} from "../sanctions.service";
import {Sanction} from "../sanction";

@Component({
  selector: 'app-sanctions-config',
  templateUrl: './sanctions-config.component.html',
  styleUrls: ['./sanctions-config.component.css']
})
export class SanctionsConfigComponent implements OnInit {
  private sanctions: Sanction[];

  constructor(private sanctionsService: SanctionsService) {
  }

  ngOnInit() {
    this.sanctionsService.getAll().subscribe(list => {
      this.sanctions = list;
    });
  }

}
