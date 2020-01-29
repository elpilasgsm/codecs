import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'performanceType'
})
export class PerformanceTypePipe implements PipeTransform {

  transform(type: any, args?: any): any {
    let label = "Неизвестный";
    if (type) {
      switch (type) {
        case "NOW":
          label = "Моментальный";
          break;
        case "STANDARD":
          label = "Стандартный";
          break;
        case "DELAYED":
          label = "Отложенный (нужно указать дату)";
          break;
        case "DELAYED_NOT_APPLIED":
          label = " Отложенный, но не вступивший в законную силу";
          break;
        default:
          label = "Неизвестный";
          break;
      }
    }
    return label;
  }

}
