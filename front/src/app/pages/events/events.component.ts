import { Component, OnInit } from '@angular/core';
import {EventService} from 'src/app/service/event.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})



export class EventsComponent implements OnInit {
  weekEvents: any;
  monthEvents: any;

  constructor(private eventService: EventService) { }

  ngOnInit() {
    console.log(this.eventService.EXAMPLE_DATA)
    this.weekEvents = this.eventService.EXAMPLE_DATA;
    this.monthEvents = this.eventService.EXAMPLE_DATA;
  }

}
