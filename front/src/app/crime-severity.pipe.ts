import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'crimeSeverity'
})
export class CrimeSeverityPipe implements PipeTransform {

  transform(type: any, args?: any): any {
    let label = "Неизвестный";
    switch (type) {
      case "MINOR":
        label = "Небольшая тяжесть";
        break;
      case "REGULAR":
        label = "Тяжкое";
        break;
      case "MIDDLE":
        label = "Средняя тяжесть";
        break;
      case "EXTRA":
        label = "Особо тяжкое";
        break;
      default:
        label = "Неизвестный";
        break;
    }
    return label;
  }

}
