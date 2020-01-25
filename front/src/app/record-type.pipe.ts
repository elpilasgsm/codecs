import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'recordType'
})
export class RecordTypePipe implements PipeTransform {

  transform(type: any, args?: any): any {
    let label = "Неизвестный";
    if (type) {
      switch (type) {
        case "ARTICLE":
          label = "Статья";
          break;
        case "POINT":
          label = "Пункт";
          break;
        case "PART":
          label = "Часть";
          break;
        default:
          label = "Неизвестный";
          break;
      }
    }
    return label;
  }

}
