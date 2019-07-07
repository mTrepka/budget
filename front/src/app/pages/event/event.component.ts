import {Component, OnInit} from '@angular/core';
import {EventService} from '../../service/event.service';
import {Event} from '../../components/Event';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit {
  types = ['Expenses', 'Revenues'];
  categories: any;
  event: Event;
  eventForm: FormGroup;

  constructor(private eventService: EventService, private formBuilder: FormBuilder) {
    this.eventForm = this.formBuilder.group({
      evName: ['', Validators.required],
      moneyId: [0, Validators.required],
      type: ['', Validators.required],
      value: [0, Validators.required],
      eventDate: ['', Validators.required],
      creationDate: [''],
      categoryId: [0, Validators.required]
    });
  }

  ngOnInit() {
    this.eventService.getCategories().subscribe(e => this.categories = e);
  }

  formToEventObject() {
    this.event = {
      evName: this.eventForm.controls.evName.value,
      moneyId: this.eventForm.controls.moneyId.value,
      type: this.eventForm.controls.type.value,
      value: this.eventForm.controls.value.value,
      eventDate: this.eventForm.controls.eventDate.value,
      creationDate: null,
      category: {
        name: '',
        categoryId: this.eventForm.controls.categoryId.value
      },
    };
  }
  send() {
    this.formToEventObject();
    this.eventService.send(this.event);
  }
}
