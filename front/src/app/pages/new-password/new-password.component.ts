import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.scss']
})
export class NewPasswordComponent implements OnInit {
  newPassword: FormGroup;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.newPassword = this.formBuilder.group({
      pass1: ['', [Validators.minLength(8), Validators.required]],
      pass2: ['']
    }, {validator: this.checkPasswords});
  }

  checkPasswords(group: FormGroup) {
    const pass1 = group.controls.pass1.value;
    const pass2 = group.controls.pass2.value;
    if (pass1 !== pass2) {
      group.controls.pass2.setErrors({notSame: true});
    }
    return pass1 !== pass2 ? {notSame: true} : null;
  }

  send() {
    this.route.queryParamMap.subscribe(queryParams => {
      const event = {
        code: queryParams.get('code'),
        userId: queryParams.get('id'),
        event: 'forgottenPassword'
      };
      const pass = {
        newPassword: this.newPassword.controls.pass1.value,
        repeatPassword: this.newPassword.controls.pass2.value
      };
      this.userService.changePassword(event, pass).subscribe(e => {
        this.router.navigate(['../login']);
      });
    });
  }

}
