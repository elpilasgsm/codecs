import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';
import {ArticleServiceService} from "../article-service.service";
import {Article} from "../article";


@Component({
  selector: 'app-article-edit',
  templateUrl: './article-edit.component.html',
  styleUrls: ['./article-edit.component.css']
})
export class ArticleEditComponent implements OnInit {
  article: Article;

  constructor(private route: ActivatedRoute, private location: Location, private articleServiceService: ArticleServiceService) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: any) => {
      if (params.id) {
        this.articleServiceService.getArticleById(params.id).subscribe(
          a => this.article = a);
      }
    })
  }
}
