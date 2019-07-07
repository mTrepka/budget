import {FormControl} from '@angular/forms';
import {UserService} from '../service/user.service';
import {map} from 'rxjs/operators';

export class ValidateUsernameNotTaken {
  static createValidator(userService: UserService) {
    return (control: FormControl) => {
      return userService.userExistByUsername(control.value).pipe(
        map(res => {
            return res ? {loginExist: true} : null;
          }
        ));
    };
  }
}
