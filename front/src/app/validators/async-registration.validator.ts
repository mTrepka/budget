import {FormControl} from '@angular/forms';
import {UserService} from '../service/user.service';
import {map} from 'rxjs/operators';

export class ValidateRegistration {
  static createEmailValidator(userService: UserService) {
    return (control: FormControl) => {
      return userService.userExistByEmail(control.value).pipe(
        map(res => {
            return res ? {emailTaken: true} : null;
          }
        ));
    };
  }

  static createUsernameValidator(userService: UserService) {
    return (control: FormControl) => {
      return userService.userExistByUsername(control.value).pipe(
        map(res => {
            return res ? {usernameTaken: true} : null;
          }
        ));
    };
  }
}
