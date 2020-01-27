import {Component, OnInit} from '@angular/core';
import {ArticleServiceService} from "./article-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'front';


  constructor(private articleService: ArticleServiceService) {

  }

  ngOnInit(): void {

  }

}
