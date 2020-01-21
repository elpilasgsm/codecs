import {Component, OnInit} from '@angular/core';
import {ArticleServiceService} from "./article-service.service";
import {Article} from "./article";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'front';
  data: Article;

  constructor(private articleService: ArticleServiceService) {

  }

  ngOnInit(): void {
    this.articleService.getArticleById("25").subscribe(val => this.data = val);
  }

}
