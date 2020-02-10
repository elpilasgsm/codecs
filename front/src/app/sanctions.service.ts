import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {MzToastService} from "ngx-materialize";
import {Sanction} from "./sanction";
import {throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SanctionsService {
  private sanctionsAPIURL = '/api/sanctions/';
  private sanctions: Sanction[];

  constructor(private http: HttpClient, private toastService: MzToastService) {

  }

  getAll(callback: (sanctions: Sanction[]) => void): void {
    if (this.sanctions) {
      callback(this.sanctions);
    }
    this.http.get<Sanction[]>(this.sanctionsAPIURL)
      .pipe(catchError(this.handleError.bind(this)))
      .subscribe(resp => {
        this.sanctions = resp;
        if (callback) {
          callback(this.sanctions);
        }
      });
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

      this.toastService.show(` Ошибка ${error.status} - ${error.error.message}`, 4000,
        'red');
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };

}
