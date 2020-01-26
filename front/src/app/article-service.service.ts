import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Article} from "./article";
import {catchError} from "rxjs/operators";
import {el} from "@angular/platform-browser/testing/src/browser_util";

@Injectable({
  providedIn: 'root'
})
export class ArticleServiceService {
  private articleAPIURL = '/api/article/';
  private tree: Article[];

  constructor(private http: HttpClient) {

  }


  getRoot(callback: (tree: Article[]) => void): void {
    if (this.tree) {
      callback(this.tree);
    } else {
      this.http.get<Article[]>(this.articleAPIURL).pipe(catchError(this.handleError)).subscribe(
        a => {
          this.tree = a;
          if (callback) {
            callback(this.tree)
          }
        });
    }
  }

  get(list: Article[], a: number): Article {
    let retVal = null;
    for (let item of list) {
      if (item.recordId.toString() === a.toString()) {
        retVal = item;
      } else if (item.children) {
        retVal = this.get(item.children, a);
      }
      if (retVal != null) {
        break;
      }
    }
    return retVal;
  }


  getArticleById(id, callback: (args: any) => void): void {
    if (this.tree) {
      callback({article: this.get(this.tree, id)});
    } else {
      this.getRoot(function (tr: Article[]) {
        callback({article: this.get(this.tree, id)});
      });
    }
  }

  deleteArticleById(id): Observable<any> {
    return this.http.delete<any>(this.articleAPIURL + id).pipe(catchError(this.handleError));
  }


  addArticle(dto: Article, callback: (args: any) => void): void {
    this.http
      .put<Article>(this.articleAPIURL, dto)
      .pipe(catchError(this.handleError))
      .subscribe(art => {
        if (!art.parent || art.parent.recordId == 0) {
          this.tree.push(art);
        } else {
          this.get(this.tree, art.parent.recordId).children.push(art)
        }
        callback(art);
      });
  }

  saveArticle(dto: Article, id: number): Observable<Article> {
    return this.http.post<Article>(`${this.articleAPIURL}${id}`, dto).pipe(catchError(this.handleError));
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
