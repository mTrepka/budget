import {Component, OnInit} from '@angular/core';
import {EventService} from 'src/app/service/event.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})


export class EventsComponent implements OnInit {
  weekEvents;
  monthEvents;
  day = (24 * 60 * 60 * 1000);

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    const todayDate = new Date();
    const today = todayDate.getFullYear() + '-' + (todayDate.getMonth() + 1) + '-' + (todayDate.getDate() + 1);
    const weekDate = new Date(todayDate.getTime() - (7 * this.day));
    const monthDate = new Date(todayDate.getTime() - (30 * this.day));
    const week = weekDate.getFullYear() + '-' + (weekDate.getMonth() + 1) + '-' + weekDate.getDate();
    const month = monthDate.getFullYear() + '-' + (monthDate.getMonth() + 1) + '-' + monthDate.getDate();
    this.eventService.getEventsByDate(week, today).subscribe(e => {
        this.weekEvents = e;
        this.weekEvents.forEach(b => b.eventDate = new Date(b.eventDate).toDateString());
      }
    );
    this.eventService.getEventsByDate(month, today).subscribe(e => {
        this.monthEvents = e;
        this.monthEvents.forEach(b => b.eventDate = new Date(b.eventDate).toDateString());
      }
    );
  }

}
