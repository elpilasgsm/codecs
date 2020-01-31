import {Component, Input, OnInit} from '@angular/core';
import {ChangesPerformanceType} from "../performance-type";
import {Changes} from "../changes";
import {Method} from "../method";

@Component({
  selector: 'app-method-changes-selector',
  templateUrl: './method-changes-selector.component.html',
  styleUrls: ['./method-changes-selector.component.css']
})
export class MethodChangesSelectorComponent implements OnInit {
  values = Method;
  @Input() change: Changes;

  constructor() {
  }

  ngOnInit() {
  }

}
