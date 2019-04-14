import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {User} from '../../components/User';
import {EventService} from '../../service/event.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  user: User;
  weekCount: number;
  monthCount: number;
  allCount: number;
  day = (24 * 60 * 60 * 1000);

  constructor(private userService: UserService, private eventService: EventService) {
  }

  ngOnInit() {
    this.userService.getUser().subscribe(e => this.user = e);
    const todayDate = new Date();
    const today = todayDate.getFullYear() + '-' + (todayDate.getMonth() + 1) + '-' + (todayDate.getDate() + 1);
    const weekDate = new Date(todayDate.getTime() - (7 * this.day));
    const monthDate = new Date(todayDate.getTime() - (30 * this.day));
    const week = weekDate.getFullYear() + '-' + (weekDate.getMonth() + 1) + '-' + weekDate.getDate();
    const month = monthDate.getFullYear() + '-' + (monthDate.getMonth() + 1) + '-' + monthDate.getDate();
    this.eventService.countEventsByDate(week, today).subscribe(e => this.weekCount = e);
    this.eventService.countEventsByDate(month, today).subscribe(e => this.monthCount = e);
    this.eventService.countEventsByDate('1900-01-01', today).subscribe(e => this.allCount = e);
  }


}
