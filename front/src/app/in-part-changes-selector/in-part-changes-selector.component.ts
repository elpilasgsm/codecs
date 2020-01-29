import {Component, Input, OnInit} from '@angular/core';
import {CRIME_SEVERITY} from "../crime-severity";
import {Article} from "../article";
import {Changes} from "../changes";
import {CodecsChangesInPart} from "../in-part";

@Component({
  selector: 'app-in-part-changes-selector',
  templateUrl: './in-part-changes-selector.component.html',
  styleUrls: ['./in-part-changes-selector.component.css']
})
export class InPartChangesSelectorComponent implements OnInit {
  values = CodecsChangesInPart;
  @Input() change: Changes;

  constructor() { }

  ngOnInit() {
  }

}
