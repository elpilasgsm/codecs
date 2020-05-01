import {Component, Input, OnInit} from '@angular/core';
import {Article} from "../article";
import {Changes} from "../changes";
import {ArticleServiceService} from "../article-service.service";
import {MzModalService, MzToastService} from "ngx-materialize";
import {ChangeEditDialogComponent} from "../change-edit-dialog/change-edit-dialog.component";
import {RecordTypePipe} from "../record-type.pipe";
import {ChangeDeleteDialogComponent} from "../change-delete-dialog/change-delete-dialog.component";

@Component({
  selector: 'app-change-edit',
  templateUrl: './change-edit.component.html',
  styleUrls: ['./change-edit.component.css']
})
export class ChangeEditComponent implements OnInit {
  @Input() article: Article;
  changes: Changes[] = [];
  private currentChange: Changes;

  constructor(private articleServiceService: ArticleServiceService,
              private modalService: MzModalService,
              private toastService: MzToastService,
              private recordTypePipe: RecordTypePipe
  ) {

  }

  addChanges() {
    this.currentChange = this.newChanges();
    this.modalService.open(ChangeEditDialogComponent, {
      change: this.currentChange,
      onSave: function (toSave: Changes) {
        this.articleServiceService.addChange(toSave, function (saved) {
          this.toastService.show(`Новое изменение ${toSave.name} в ${this.recordTypePipe.transform(this.article.recordType, null)} ${this.article.name} успешно добавлено!`,
            4000,
            'green');
          this.ngOnChanges();
        }.bind(this));
      }.bind(this)
    });
  }

  editChange(change: Changes) {
    this.currentChange = Object.assign({}, change);
    this.modalService.open(ChangeEditDialogComponent, {
      change: this.currentChange,
      onSave: function (toSave: Changes) {
        this.articleServiceService.editChange(toSave, toSave.id).subscribe(ch => {
          this.toastService.show(`Изменение ${toSave.name} в ${this.recordTypePipe.transform(this.article.recordType, null)} ${this.article.name} успешно сохранено!`,
            4000,
            'green');
          this.ngOnChanges();
        });
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
      activationDate: null,
      direction: 'POSITIVE',
      method: 'APPEND',
      date: new Date(1996, 0, 1),
      primarySanctions: [],
      alternateSanctions: [],
      url: null,
      record: this.article
    };
  }

  delete(ch: Changes): void {
    this.modalService.open(ChangeDeleteDialogComponent, {
      change: ch,
      onAgree: function () {
        this.articleServiceService.deleteChangeById(ch.id).subscribe(a => {
          if (200 === a) {
            this.articleServiceService.getRoot(function (tree: Article[]) {
              this.toastService.show(`Изменение ${ch.name} успешно удалено!`,
                4000,
                'green');
              this.ngOnChanges();
            }.bind(this));
          }
        });
      }.bind(this)
    });

    /*    */
  }

  ngOnInit() {
    if (this.article) {
      this.articleServiceService.getChangesForArticleById(this.article.recordId, function (changes: Changes[]) {
        this.changes = changes;
      }.bind(this));
    }
  }

  ngOnChanges() {
    this.ngOnInit()
  }
}
