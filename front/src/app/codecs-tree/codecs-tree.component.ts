import {Component, OnInit} from '@angular/core';
import {Article} from "../article";
import {ArticleServiceService} from "../article-service.service";

@Component({
  selector: 'app-codecs-tree',
  templateUrl: './codecs-tree.component.html',
  styleUrls: ['./codecs-tree.component.css']
})
export class CodecsTreeComponent implements OnInit {
  private codecsTree: Article[];

  constructor(private articleService: ArticleServiceService) {
  }

  ngOnInit() {
    this.articleService.getRoot(this.callback.bind(this));
  }

  private callback(tree: Article[]) {
    this.codecsTree = tree;
  }

}


