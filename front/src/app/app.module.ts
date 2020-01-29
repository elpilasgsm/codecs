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
import {MzButtonModule, MzDatepickerModule, MzModalModule, MzSelectModule, MzToastModule} from "ngx-materialize";
import {DeleteArticleModalComponent} from './delete-article-modal/delete-article-modal.component';
import {RecordTypeShortPipe} from './record-type-short.pipe';
import {MethodPipe} from './method.pipe';
import {ChangeEditComponent} from './change-edit/change-edit.component';
import {ChangeEditDialogComponent} from './change-edit-dialog/change-edit-dialog.component';
import { InPartChangesSelectorComponent } from './in-part-changes-selector/in-part-changes-selector.component';
import { InPartPipe } from './in-part.pipe';
import { PerformanceChangesSelectorComponent } from './performance-changes-selector/performance-changes-selector.component';
import { PerformanceTypePipe } from './performance-type.pipe';
import { DirectionPipe } from './direction.pipe';
import { DirectionChangesSelectorComponent } from './direction-changes-selector/direction-changes-selector.component';
import { MethodChangesSelectorComponent } from './method-changes-selector/method-changes-selector.component';

@NgModule({
  declarations: [
    AppComponent,
    CodecsTreeComponent,
    CodecsTreeRowComponent,
    ArticleEditComponent,
    RecordTypePipe,
    CrimeSeverityPipe,
    CrimeSereritySelectorComponent,
    DeleteArticleModalComponent,
    RecordTypeShortPipe,
    MethodPipe,
    ChangeEditComponent,
    ChangeEditDialogComponent,
    InPartChangesSelectorComponent,
    InPartPipe,
    PerformanceChangesSelectorComponent,
    PerformanceTypePipe,
    DirectionPipe,
    DirectionChangesSelectorComponent,
    MethodChangesSelectorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MzSelectModule,
    MzDatepickerModule,
    MzModalModule,
    MzButtonModule,
    MzToastModule
  ],
  exports: [RecordTypePipe],
  providers: [RecordTypePipe],
  bootstrap: [AppComponent],
  entryComponents: [DeleteArticleModalComponent, ChangeEditDialogComponent],

})
export class AppModule {
}
