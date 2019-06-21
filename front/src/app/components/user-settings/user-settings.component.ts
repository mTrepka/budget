import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MyErrorStateMatcher} from 'src/app/helpers/my-error-state-matcher';


@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.scss']
})
export class UserSettingsComponent implements OnInit {
  settingsForm: FormGroup;
  matcher = new MyErrorStateMatcher();
  
  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.settingsForm = this.formBuilder.group({
      surname: [''],
      username: [''],
      uname: [''],
      password: ['', Validators.required],
      pass1: [''],
      pass2: ['']
    }, {validators: this.checkPasswords});
  }

  checkPasswords(group: FormGroup) {
    const pass1 = group.controls.pass1.value;
    const pass2 = group.controls.pass2.value;
    return pass1 === pass2 ? null : {notSame: true};
  }

  update() {
    this.userService.update(this.settingsForm.value).subscribe();
    window.location.reload();
  }

}
