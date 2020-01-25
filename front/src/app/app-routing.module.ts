import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ArticleEditComponent} from "./article-edit/article-edit.component";

const routes: Routes = [
  { path: 'article-edit/:id', component: ArticleEditComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
