import {Changes} from "./changes";

export class Article {
  public recordId: number;
  public name: string;
  public recordType: string;
  public url: string;
  public abbreviation: string;
  public parent: Article;
  public children: Article[];
  public changes: Changes[];
}
