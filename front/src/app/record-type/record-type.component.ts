import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-record-type',
  templateUrl: './record-type.component.html',
  styleUrls: ['./record-type.component.css']
})
export class RecordTypeComponent implements OnInit {
  @Input() type: string;
  label: string = "Неизвестный";

  constructor() {
  }

  ngOnInit() {
    if (this.type) {
      switch (this.type) {
        case "ARTICLE":
          this.label = "Статья";
          break;
        case "POINT":
          this.label = "Пункт";
          break;
        case "PART":
          this.label = "Часть";
          break;
        default:
          this.label = "Неизвестный";
          break;
      }
    }
  }

}
