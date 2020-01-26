import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';
import {ArticleServiceService} from "../article-service.service";
import {Article} from "../article";
import {CodecsTreeComponent} from "../codecs-tree/codecs-tree.component";


@Component({
  selector: 'app-article-edit',
  templateUrl: './article-edit.component.html',
  styleUrls: ['./article-edit.component.css']
})
export class ArticleEditComponent implements OnInit {
  article: Article;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private articleServiceService: ArticleServiceService
  ) {

  }

  save(): void {
    let isNew = this.article.recordId === null;
    if (isNew) {
      this.articleServiceService.addArticle(this.article).subscribe(a => this.article = a);
    } else {
      this.articleServiceService.saveArticle(this.article, this.article.recordId).subscribe(a => {
        this.article.url = a.url;
        this.article.crimeSeverity = a.crimeSeverity;
        this.article.name = a.name;
      });
    }
  }

  update(list: Article[], a: Article): void {
    list.forEach((item, index) => {
      if (item.recordId === a.recordId) {
        item.name = a.name;
        item.crimeSeverity = a.crimeSeverity;
        item.url = a.url;
        return;
      } else if (item.children) {
        this.update(item.children, a);
      }
    });
  }

  ngOnInit() {
    this.route.params.subscribe((params: any) => {
      if (params.id) {
        if (params.id === 'new') {
          this.article = {
            recordId: null,
            crimeSeverity: null,
            recordType: 'ARTICLE',
            name: null,
            children: null,
            changes: null,
            parent: null,
            url: null
          };
        } else {
          this.articleServiceService.getArticleById(params.id, function (args) {
            this.article = args.article;
          }.bind(this));
        }
      }
    });
  }
}
