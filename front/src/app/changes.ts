import {Article} from "./article";
import {SanctionChange} from "./sanction-change";

export class Changes {
  public id: number; //done
  public name: string; //done
  public changesInPart: string; //done
  public performanceType: string; //done
  public crimeSeverity: string; //done
  public activationDate: Date; //date
  public direction: string;
  public method: string;
  public date: Date; //date
  public url: string; //done
  public record: Article;
  public primarySanctions: SanctionChange[];
}
