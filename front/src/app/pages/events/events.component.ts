import { Component, OnInit } from '@angular/core';
import {EventService} from 'src/app/service/event.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})



export class EventsComponent implements OnInit {
<<<<<<< HEAD
  weekEvents: any;
  monthEvents: any;
=======
  weekEvents;
  monthEvents;
>>>>>>> origin/master

  constructor(private eventService: EventService) { }

  ngOnInit() {
<<<<<<< HEAD
    console.log(this.eventService.EXAMPLE_DATA)
    this.weekEvents = this.eventService.EXAMPLE_DATA;
    this.monthEvents = this.eventService.EXAMPLE_DATA;
=======
    this.eventService.getEvents().subscribe(e => {
      this.weekEvents = e;
      this.monthEvents = e;
    });
>>>>>>> origin/master
  }

}
