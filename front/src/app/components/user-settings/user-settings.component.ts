import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.scss']
})
export class UserSettingsComponent implements OnInit {
  user;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.user = {
      surname: '',
      username: '',
      uname: '',
      password: '',
      pass1: '',
      pass2: ''
    };
  }

  update() {
    if (this.user.password === '') {
      alert('No password');
    } else {
      if (this.user.pass1 !== this.user.pass2) {
        alert('Password are different');
      } else {
        this.userService.update(this.user).subscribe();
      }
    }
    window.location.reload();
  }
}
