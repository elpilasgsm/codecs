import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'direction'
})
export class DirectionPipe implements PipeTransform {

  transform(type: any, args?: any): any {
    let label = "Неизвестный";
    if (type) {
      switch (type) {
        case "NEGATIVE":
          label = "Cмягчение закона";
          break;
        case "NEUTRAL":
          label = "Нейтральный характер изменений";
          break;
        case "POSITIVE":
          label = "Ужесточение закона";
          break;
        default:
          label = "Неизвестный";
          break;
      }
    }
    return label;
  }
}
