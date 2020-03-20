import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Article} from "./article";
import {catchError} from "rxjs/operators";
import {MzToastService} from "ngx-materialize";
import {Changes} from "./changes";

@Injectable({
  providedIn: 'root'
})
export class ArticleServiceService {
  private articleAPIURL = '/api/article/';
  private changeAPIURL = '/api/change/';
  private tree: Article[] = [];

  constructor(private http: HttpClient, private toastService: MzToastService) {

  }


  getRoot(callback: (tree: Article[]) => void): void {
    if (this.tree && this.tree.length > 0) {
      callback(this.tree);
    } else {
      this.http.get<Article[]>(this.articleAPIURL).pipe(catchError(this.handleError.bind(this))).subscribe(
        a => {
          this.tree.push.apply(this.tree, a);
          if (callback) {
            callback(this.tree)
          }
        });
    }
  }


  refreshTree(): void {
    this.tree = null;
    this.getRoot(null)
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
    if (this.tree && this.tree.length > 0) {
      callback({article: this.get(this.tree, id)});
    } else {
      this.getRoot(function (tr: Article[]) {
        callback({article: this.get(this.tree, id)});
      }.bind(this));
    }
  }

  getChangesForArticleById(id, callback: (args: any) => void): void {
    if (id) {
      this.http.get<Changes[]>(`${this.articleAPIURL}${id}/changes`).pipe(catchError(this.handleError.bind(this))).subscribe(
        changes => {
          if (callback) {
            callback(changes);
          }
        });
    }
  }


  deleteArticleById(id): Observable<any> {
    return this.http.delete<any>(this.articleAPIURL + id).pipe(catchError(this.handleError.bind(this)));
  }

  deleteChangeById(id): Observable<any> {
    return this.http.delete<any>(this.changeAPIURL + id).pipe(catchError(this.handleError.bind(this)));
  }

  addArticle(dto: Article, callback: (args: any) => void): void {
    this.http
      .put<Article>(this.articleAPIURL, dto)
      .pipe(catchError(this.handleError.bind(this)))
      .subscribe(art => {
        this.tree.length = 0;
        this.getRoot(function (tree) {
          callback(art);
        }.bind(this));
      });
  }

  addChange(dto: Changes, callback: (args: any) => void): void {
    this.http
      .put<Article>(this.changeAPIURL, dto)
      .pipe(catchError(this.handleError.bind(this)))
      .subscribe(art => {
        this.getRoot(function (tree) {
          callback(art);
        }.bind(this));

      });
  }

  saveArticle(dto: Article, id: number): Observable<Article> {
    return this.http.post<Article>(`${this.articleAPIURL}${id}`, dto).pipe(catchError(this.handleError.bind(this)));
  }

  editChange(dto: Changes, id: number): Observable<Changes> {
    return this.http.post<Changes>(`${this.changeAPIURL}${id}`, dto).pipe(catchError(this.handleError.bind(this)));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);

      this.toastService.show(`Ошибка ${error.error.message}`,
        4000,
        'red');
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);

      this.toastService.show(` Ошибка ${error.status} - ${error.error}`, 4000,
        'red');
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}
