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
  public dateOptions: Pickadate.DateOptions = {
    clear: 'Clear', // Clear button text
    close: 'Ok',    // Ok button text
    today: 'Today', // Today button text
    closeOnClear: true,
    closeOnSelect: false,
    format: 'dd/mm/yyyy', // Visible date format (defaulted to formatSubmit if provided otherwise 'd mmmm, yyyy')
    formatSubmit: 'yyyy-mm-dd',   // Return value format (used to set/get value)
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 10,    // Creates a dropdown of 10 years to control year,
  };

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
