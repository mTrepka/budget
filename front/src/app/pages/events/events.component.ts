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
  types = ['Expenses', 'Revenues'];
  categories;
  filterLowValue;
  filterHighValue;
  filterStartDate;
  filterEndDate;
  filterType;
  filterEventName = '';
  filterCategory;
  allEvents;
  filteredEvents;
  day = (24 * 60 * 60 * 1000);

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    const todayDate = new Date();
    const today = todayDate.getFullYear() + '-' + (todayDate.getMonth() + 1) + '-' + (todayDate.getDate() + 1);
    const weekAgo = new Date(todayDate.getTime() - (6 * this.day));
    const monthAgo = new Date(todayDate.getTime() - (30 * this.day));
    const weekAgoDay = weekAgo.getFullYear() + '-' + (weekAgo.getMonth() + 1) + '-' + weekAgo.getDate();
    const monthAgoDay = monthAgo.getFullYear() + '-' + (monthAgo.getMonth() + 1) + '-' + monthAgo.getDate();

    this.eventService.getEventsByDate(weekAgoDay, today).subscribe(e => {
        this.weekEvents = e;
      this.dateParser(this.weekEvents);
      }
    );

    this.eventService.getEvents().subscribe(e => {
      this.allEvents = e;
      this.filteredEvents = e;
      this.dateParser(this.allEvents);
    });

    this.eventService.getEventsByDate(monthAgoDay, today).subscribe(e => {
        this.monthEvents = e;
      this.dateParser(this.monthEvents);
      }
    );

    this.eventService.getCategories().subscribe(e => this.categories = e);
  }

  dateParser(date) {
    date.forEach(b => b.eventDate = new Date(b.eventDate).toDateString());
  }

  filter() {
    this.filteredEvents = this.allEvents;
    if (this.filterType !== undefined) {
      this.filteredEvents = this.filteredEvents.filter(e => e.type === this.filterType);
    }

    if (this.filterEventName !== '') {
      this.filteredEvents = this.filteredEvents
        .filter(e => e.evName.toLocaleLowerCase().includes(this.filterEventName.toLocaleLowerCase()));
    }

    if (this.filterCategory !== undefined) {
      this.filteredEvents = this.filteredEvents.filter(e => e.category.name === this.filterCategory);
    }

    if (this.filterLowValue !== null && this.filterLowValue !== undefined) {
      this.filteredEvents = this.filteredEvents.filter(e => e.value >= this.filterLowValue);
    }

    if (this.filterHighValue !== null && this.filterHighValue !== undefined) {
      this.filteredEvents = this.filteredEvents.filter(e => e.value <= this.filterHighValue);
    }

    if (this.filterStartDate !== null && this.filterStartDate !== undefined) {
      this.filteredEvents = this.filteredEvents.filter(e => new Date(e.eventDate) >= this.filterStartDate);
    }

    if (this.filterEndDate !== null && this.filterEndDate !== undefined) {
      this.filteredEvents = this.filteredEvents.filter(e => new Date(e.eventDate) <= this.filterEndDate);
    }
  }
}
