import {Component, Input, OnInit} from '@angular/core';
import {Method} from "../method";
import {Changes} from "../changes";
import {ChangesDirection} from "../direction";

@Component({
  selector: 'app-direction-changes-selector',
  templateUrl: './direction-changes-selector.component.html',
  styleUrls: ['./direction-changes-selector.component.css']
})
export class DirectionChangesSelectorComponent implements OnInit {
  values = ChangesDirection;
  @Input() change: Changes;

  constructor() {
  }

  ngOnInit() {
  }

}
