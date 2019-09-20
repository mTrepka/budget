import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.scss']
})
export class ForgotpasswordComponent implements OnInit {
  email;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.email = '';
  }

  send() {
    this.userService.forgotPassword(this.email).subscribe();
  }

}
