import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'recordTypeShort'
})
export class RecordTypeShortPipe implements PipeTransform {

  transform(type: any, args?: any): any {
    let label = " ";
    if (type) {
      switch (type) {
        case "ARTICLE":
          label = "Ст.";
          break;
        case "POINT":
          label = "п.";
          break;
        case "PART":
          label = "ч.";
          break;
        default:
          label = " ";
          break;
      }
    }
    return label;
  }

}
