import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { ArticleRowComponent } from './article-row/article-row.component';
import {FormsModule} from "@angular/forms";
import { RecordTypeComponent } from './record-type/record-type.component';
import { CodecsTreeComponent } from './codecs-tree/codecs-tree.component';
import { CrimeSeverityViewComponent } from './crime-severity-view/crime-severity-view.component';
import { CodecsTreeRowComponent } from './codecs-tree-row/codecs-tree-row.component';

@NgModule({
  declarations: [
    AppComponent,
    ArticleRowComponent,
    RecordTypeComponent,
    CodecsTreeComponent,
    CrimeSeverityViewComponent,
    CodecsTreeRowComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
