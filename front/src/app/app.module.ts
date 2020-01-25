import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {CodecsTreeComponent} from './codecs-tree/codecs-tree.component';
import {CodecsTreeRowComponent} from './codecs-tree-row/codecs-tree-row.component';
import {ArticleEditComponent} from './article-edit/article-edit.component';
import {RecordTypePipe} from './record-type.pipe';
import {CrimeSeverityPipe} from './crime-severity.pipe';
import {CrimeSereritySelectorComponent} from './crime-sererity-selector/crime-sererity-selector.component';
import {MzDatepickerModule, MzSelectModule} from "ngx-materialize";

@NgModule({
  declarations: [
    AppComponent,
    CodecsTreeComponent,
    CodecsTreeRowComponent,
    ArticleEditComponent,
    RecordTypePipe,
    CrimeSeverityPipe,
    CrimeSereritySelectorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MzSelectModule,
    MzDatepickerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
