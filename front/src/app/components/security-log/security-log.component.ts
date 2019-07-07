import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-security-log',
  templateUrl: './security-log.component.html',
  styleUrls: ['./security-log.component.scss']
})
export class SecurityLogComponent implements OnInit {
  securityLog;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUserSecuritylog().subscribe(e => {
      this.securityLog = e;
      this.dateParser(this.securityLog);
    });
  }

  dateParser(log) {
    log.forEach(b => b.date = new Date(b.date).toDateString());
  }

}
