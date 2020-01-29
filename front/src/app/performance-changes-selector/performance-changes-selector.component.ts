import {Component, Input, OnInit} from '@angular/core';
import {CodecsChangesInPart} from "../in-part";
import {Changes} from "../changes";
import {ChangesPerformanceType} from "../performance-type";

@Component({
  selector: 'app-performance-changes-selector',
  templateUrl: './performance-changes-selector.component.html',
  styleUrls: ['./performance-changes-selector.component.css']
})
export class PerformanceChangesSelectorComponent implements OnInit {
  values = ChangesPerformanceType;
  @Input() change: Changes;


  constructor() { }

  ngOnInit() {
  }

}
