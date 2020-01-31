import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'inPart'
})
export class InPartPipe implements PipeTransform {

  transform(type: any, args?: any): any {
    let label = "Неизвестный";
    if (type) {
      switch (type) {
        case "OFFENSE":
          label = "Преступление";
          break;
        case "PUNISHMENT":
          label = "Наказание";
          break;
        case "BOTH":
          label = "Оба";
          break;
        default:
          label = "Неизвестный";
          break;
      }
    }
    return label;
  }

}
