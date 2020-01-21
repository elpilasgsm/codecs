import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Article} from "./article";

@Injectable({
  providedIn: 'root'
})
export class ArticleServiceService {
  private getArticleURL = '/api/article/';

  constructor(private http: HttpClient) {

  }

  getArticleById(id): Observable<Article> {
    console.log("ID is " + this.getArticleURL + id);
    return this.http.get<Article>(this.getArticleURL + id);
  }
}
