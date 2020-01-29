import {Component, Input, OnInit} from '@angular/core';
import {Article} from "../article";
import {Changes} from "../changes";
import {ArticleServiceService} from "../article-service.service";
import {CRIME_SEVERITY} from "../crime-severity";

@Component({
  selector: 'app-change-edit',
  templateUrl: './change-edit.component.html',
  styleUrls: ['./change-edit.component.css']
})
export class ChangeEditComponent implements OnInit {
  @Input() article: Article;
  private changes: Changes[] = [];

  constructor(private articleServiceService: ArticleServiceService) {
  }

  addChanges() {

  }

  newChanges(): Changes {
    return {
      id: null,
      name: null,
      changesInPart: 'OFFENSE',
      performanceType: 'NOW',
      crimeSeverity: 'NOT_APPLIED',
      activationDate: new Date(),
      direction: 'POSITIVE',
      method: 'APPEND',
      date: new Date(),
      url: null,
      record: this.article
    };
  }


  ngOnInit() {
    if (this.article) {
      this.articleServiceService.getChangesForArticleById(this.article.recordId, function (changes: Changes[]) {
        this.changes.push.apply(changes, this.changes);
      }.bind(this));
    }
  }

}
