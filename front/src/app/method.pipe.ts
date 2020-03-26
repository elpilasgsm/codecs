import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'method'
})
export class MethodPipe implements PipeTransform {

  transform(type: any, args?: any): any {
    let label = "Неизвестный";
    if (type) {
      switch (type) {
        case "APPEND":
          label = "Дополнить";
          break;
        case "CHANGE":
          label = "Изменить";
          break;
        case "EXCLUDE":
          label = "Исключить/утратить силу";
          break;
        case "NOT_CONSTITUTIONAL":
          label = "Признать неконституционной норму закона";
          break;
        case "NEW":
          label = "Первоначальная редакция";
          break;
        default:
          label = " ";
          break;
      }
    }
    return label;
  }


}
