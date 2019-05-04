import {Component, OnInit} from '@angular/core';
import {EventService} from '../../service/event.service';
import {Event} from '../../components/Event';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit {
  types = ['Wydatki', 'Przychody'];
  categories: any;
  event: Event;

  constructor(private eventService: EventService) {
  }
  date = new FormControl(new Date());

  ngOnInit() {
    this.event = {
      evName: '',
      moneyId : 0,
      type: '',
      value: 0,
      eventDate: null,
      creationDate: null,
      category: {
        name: '',
        categoryId: 0
      },
    };
    this.eventService.getCategories().subscribe(e => this.categories = e);
  }

  send() {
    this.eventService.send(this.event);
  }
}
