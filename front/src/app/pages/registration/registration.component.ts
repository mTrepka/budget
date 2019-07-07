import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../service/user.service';
import {ValidateRegistration} from '../../validators/async-registration.validator';
import {Router} from '@angular/router';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private router: Router) {
  }

  ngOnInit() {

    this.registrationForm = this.formBuilder.group({
      surname: [''],
      username: ['', [Validators.required], ValidateRegistration.createUsernameValidator(this.userService)],
      uname: [''],
      email: ['', [Validators.required], ValidateRegistration.createEmailValidator(this.userService)],
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

  update() {
    this.userService.registration(this.registrationForm.value).subscribe();
    alert('User has been registered, you can now login');
    this.router.navigate(['/login']);
  }

}
