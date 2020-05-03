import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'optional'
})
export class OptionalPipe implements PipeTransform {

  transform(value: boolean, args?: any): any {
    return value ? "Необязательно: " : "";
  }

}
