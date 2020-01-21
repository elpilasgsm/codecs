export class Article {
  private recordId: number;
  private name: string;
  private recordType: string;
  private changesInPart: string;
  private performanceType: string;
  private activationDate: Date;
  private direction: string;
  private date: Date;
  private url: string;
  private parent: Article;
  private children: Article[];
}
