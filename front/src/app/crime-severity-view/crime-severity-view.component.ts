import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-crime-severity-view',
  templateUrl: './crime-severity-view.component.html',
  styleUrls: ['./crime-severity-view.component.css']
})
export class CrimeSeverityViewComponent implements OnInit {
  @Input() type: string;
  label: string = "Неизвестный";

  constructor() {
  }

  ngOnInit() {
    switch (this.type) {
      case "MINOR":
        this.label = "Небольшая тяжесть";
        break;
      case "REGULAR":
        this.label = "Тяжкое";
        break;
      case "MIDDLE":
        this.label = "Средняя тяжесть";
        break;
      case "EXTRA":
        this.label = "Особо тяжкое";
        break;
      default:
        this.label = "Неизвестный";
        break;
    }
  }

}
