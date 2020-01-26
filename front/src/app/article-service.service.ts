import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Article} from "./article";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ArticleServiceService {
  private articleAPIURL = '/api/article/';

  constructor(private http: HttpClient) {

  }


  getRoot(): Observable<Article[]> {
    return this.http.get<Article[]>(this.articleAPIURL).pipe(catchError(this.handleError));
  }

  getArticleById(id): Observable<Article> {
    return this.http.get<Article>(this.articleAPIURL + id).pipe(catchError(this.handleError));
  }

  deleteArticleById(id): Observable<any> {
    return this.http.delete<any>(this.articleAPIURL + id).pipe(catchError(this.handleError));
  }


  addArticle(dto: Article): Observable<Article> {
    return this.http.put<Article>(this.articleAPIURL, dto).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}
