import { Component, OnInit } from '@angular/core';
import {EventService} from 'src/app/service/event.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})



export class EventsComponent implements OnInit {
  weekEvents;
  monthEvents;

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.eventService.getEvents().subscribe(e => {
      this.weekEvents = e;
      this.monthEvents = e;
    });
  }

}
