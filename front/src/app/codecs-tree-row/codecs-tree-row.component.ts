import {Component, Input, OnInit} from '@angular/core';
import {Article} from "../article";

@Component({
  selector: 'app-codecs-tree-row',
  templateUrl: './codecs-tree-row.component.html',
  styleUrls: ['./codecs-tree-row.component.css']
})
export class CodecsTreeRowComponent implements OnInit {
  @Input() row: Article;

  constructor() {
  }

  ngOnInit() {
  }

}
