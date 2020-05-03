import {Changes} from "./changes";
import {Sanction} from "./sanction";

export class SanctionChange {
  public id: number;
  public from: number;
  public to: number;
  public optional: boolean;
  public change: Changes;
  public sanction: Sanction;
  public alternateSanctions: SanctionChange[];
  public mainSanction: SanctionChange;
}
