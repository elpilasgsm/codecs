import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ArticleEditComponent} from "./article-edit/article-edit.component";
import {DefaultViewComponent} from "./default-view/default-view.component";
import {SanctionsConfigComponent} from "./sanctions-config/sanctions-config.component";

const routes: Routes = [
  {path: '', component: DefaultViewComponent, pathMatch: 'full'},
  {path: 'article-edit/:id', component: ArticleEditComponent, pathMatch: 'full'},
  {path: 'new', component: ArticleEditComponent, pathMatch: 'full'},
  {path: 'sanctions-conf', component: SanctionsConfigComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
