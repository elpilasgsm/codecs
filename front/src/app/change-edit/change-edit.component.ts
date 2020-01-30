import {Component, Input, OnInit} from '@angular/core';
import {Article} from "../article";
import {Changes} from "../changes";
import {ArticleServiceService} from "../article-service.service";
import {CRIME_SEVERITY} from "../crime-severity";
import {MzModalService, MzToastService} from "ngx-materialize";
import {DeleteArticleModalComponent} from "../delete-article-modal/delete-article-modal.component";
import {ChangeEditDialogComponent} from "../change-edit-dialog/change-edit-dialog.component";

@Component({
  selector: 'app-change-edit',
  templateUrl: './change-edit.component.html',
  styleUrls: ['./change-edit.component.css']
})
export class ChangeEditComponent implements OnInit {
  @Input() article: Article;
  private changes: Changes[] = [];
  private currentChange: Changes;

  constructor(private articleServiceService: ArticleServiceService,
              private modalService: MzModalService,
              private toastService: MzToastService) {
  }

  addChanges() {
    this.currentChange = this.newChanges();
    this.modalService.open(ChangeEditDialogComponent, {
      change: this.currentChange,
      onSave: function () {
        this.articleServiceService.addChange(this.change, function () {

          this.changes.push(this.currentChange);
        }.bind(this));
      }.bind(this)
    });
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
