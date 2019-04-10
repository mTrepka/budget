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
<<<<<<< HEAD
  types = ['wydatek', 'przychod'];
=======
  types = ['wyd', 'prz'];
>>>>>>> 106066468205e1d7d8eba1f67ab92ec7cc0e6003
  categories: any;
  event: Event;

  constructor(private eventService: EventService) {
  }
  date = new FormControl(new Date());
  serializedDate = new FormControl((new Date()).toISOString());

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
    console.log(this.event);
    this.eventService.send(this.event);
  }
}
