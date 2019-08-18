import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../../service/authentication.service';

@Component({
  selector: 'app-social-login',
  templateUrl: './social-login.component.html',
  styleUrls: ['./social-login.component.scss']
})
export class SocialLoginComponent implements OnInit {

  constructor(private route: ActivatedRoute, private auth: AuthenticationService, private router: Router) {
    this.route.queryParamMap.subscribe(queryParams => {
      this.auth.loginViaToken(queryParams.get('token'));
    });

  }

  ngOnInit() {
  }


}
