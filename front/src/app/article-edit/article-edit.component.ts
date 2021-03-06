import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {Location} from '@angular/common';
import {ArticleServiceService} from "../article-service.service";
import {Article} from "../article";
import {CodecsTreeComponent} from "../codecs-tree/codecs-tree.component";
import {DeleteArticleModalComponent} from "../delete-article-modal/delete-article-modal.component";
import {MzModalService, MzToastService} from "ngx-materialize";
import {RecordTypePipe} from "../record-type.pipe";
import {Changes} from "../changes";


@Component({
  selector: 'app-article-edit',
  templateUrl: './article-edit.component.html',
  styleUrls: ['./article-edit.component.css']
})
export class ArticleEditComponent implements OnInit {
  article: Article;
  originArticle: Article;
  parentArticle: Article;
  changes: Changes[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private articleServiceService: ArticleServiceService,
              private modalService: MzModalService,
              private toastService: MzToastService,
              private recordTypePipe: RecordTypePipe
  ) {

  }

  save(): void {
    let isNew = this.article.recordId === null;
    if (isNew) {
      this.articleServiceService.addArticle(this.article, function (a) {
        this.toastService.show(`${this.recordTypePipe.transform(a.recordType, null)} ${a.name} успешно добавлен(а)!`,
          4000,
          'green');
        this.article = Object.assign({}, a);
        this.router.navigate([`/article-edit/${a.recordId}`], {skipLocationChange: true});
      }.bind(this));
    } else {
      this.articleServiceService.saveArticle(this.article, this.article.recordId).subscribe(a => {
        this.toastService.show(`${this.recordTypePipe.transform(a.recordType, null)} ${a.name} успешно сохранен(а)!`,
          4000,
          'green');
        this.article.url = a.url;
        this.article.name = a.name;
        Object.assign(this.originArticle, this.article);
      });
    }
  }

  deleteFromTree(list: Article[], rec: Article): void {
    list.forEach((item, index) => {
      if (item.recordId === rec.recordId) {
        list.splice(index, 1);
        return;
      } else if (item.children) {
        this.deleteFromTree(item.children, rec);
      }
    });
  }

  delete(): void {
    this.modalService.open(DeleteArticleModalComponent, {
      article: this.article,
      onAgree: function () {
        this.router.navigate([`/`], {skipLocationChange: true});
        this.articleServiceService.deleteArticleById(this.article.recordId).subscribe(a => {
          if (200 === a) {
            this.articleServiceService.getRoot(function (tree: Article[]) {
              this.toastService.show(`${this.recordTypePipe.transform(this.article.recordType, null)} ${this.article.name} успешно удален(а)!`,
                4000,
                'green');
              this.deleteFromTree(tree, this.article);
            }.bind(this));
          }
        });
      }.bind(this)
    });

    /*    */
  }

  newArticle(): Article {
    return {
      recordId: null,
      recordType: 'ARTICLE',
      name: null,
      children: null,
      changes: null,
      parent: this.parentArticle,
      url: null,
      abbreviation: null
    }
  }

  cancel() {
    this.article = Object.assign({}, this.originArticle);
    this.router.navigate([`/`], {skipLocationChange: true});
  }

  ngOnInit() {
    this.route.params.subscribe((params: any) => {
      if (params.id) {
        if (params.id === 'new') {
          this.route.queryParamMap.subscribe((params: ParamMap) => {
            if (params.get('parentId')) {
              this.articleServiceService.getArticleById(
                params.get('parentId'), function (any) {
                  this.parentArticle = any.article;
                  this.article = this.newArticle();
                  this.article.recordType = this.parentArticle == null ? 'ARTICLE'
                    : (params.get("parentType") == 'ARTICLE' ? 'PART' : 'POINT');
                  this.changes = [];
                }.bind(this)
              );
            } else {
              this.article = this.newArticle();
              this.changes = [];
            }
          });
        } else {
          this.articleServiceService.getArticleById(params.id, function (args) {
            this.originArticle = args.article;
            this.article = Object.assign({}, this.originArticle);
          }.bind(this));
        }
      }
    });
  }
}
