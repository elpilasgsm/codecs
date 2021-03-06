import {Component, Input, OnInit} from '@angular/core';
import {CRIME_SEVERITY} from '../crime-severity';
import {Article} from "../article";
import {Changes} from "../changes";

@Component({
  selector: 'app-crime-sererity-selector',
  templateUrl: './crime-sererity-selector.component.html',
  styleUrls: ['./crime-sererity-selector.component.css']
})
export class CrimeSereritySelectorComponent implements OnInit {
  values = CRIME_SEVERITY;
  @Input() article: Article;
  @Input() change: Changes;

  constructor() {
  }

  ngOnInit() {

  }
}
