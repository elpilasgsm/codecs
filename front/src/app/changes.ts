import {Article} from "./article";

export class Changes {
  public id: number;
  public name: string;
  public changesInPart: string;
  public performanceType: string;
  public crimeSeverity: string;
  public activationDate: Date;
  public direction: string;
  public date: Date;
  public url: string;
  public record: Article;
}
