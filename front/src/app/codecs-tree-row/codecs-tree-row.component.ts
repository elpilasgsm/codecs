import {Component, Input, OnInit} from '@angular/core';
import {Article} from "../article";
import {ArticleServiceService} from "../article-service.service";
import {MzModalService} from "ngx-materialize";
import {DeleteArticleModalComponent} from "../delete-article-modal/delete-article-modal.component";

@Component({
  selector: 'app-codecs-tree-row',
  templateUrl: './codecs-tree-row.component.html',
  styleUrls: ['./codecs-tree-row.component.css']
})
export class CodecsTreeRowComponent implements OnInit {
  @Input() row: Article;
  @Input() data: Article[];

  constructor(private articleServiceService: ArticleServiceService, private modalService: MzModalService) {
  }

  ngOnInit() {
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
      article: this.row,
      onAgree: function () {
        this.articleServiceService.deleteArticleById(this.row.recordId).subscribe(a => {
          if (200 === a) {
            this.deleteFromTree(this.data, this.row);
          }
        });
      }.bind(this)
    });

    /*    */
  }

}
