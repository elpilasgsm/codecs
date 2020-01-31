import {Component, Input, OnInit} from '@angular/core';
import {Article} from "../article";
import {ArticleServiceService} from "../article-service.service";
import {MzModalService} from "ngx-materialize";
import {DeleteArticleModalComponent} from "../delete-article-modal/delete-article-modal.component";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-codecs-tree-row',
  templateUrl: './codecs-tree-row.component.html',
  styleUrls: ['./codecs-tree-row.component.css']
})
export class CodecsTreeRowComponent implements OnInit {
  @Input() row: Article;


  constructor(private router: Router) {
  }

  onClick() {
    this.router.navigate([`/article-edit/${this.row.recordId}`], {skipLocationChange:true});
  }

  ngOnInit() {

  }

}
