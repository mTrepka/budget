import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})


export class EventService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  getEvents() {
    return this.http.get<Array<Event>>('http://localhost:8080/event/');
  }

  getEventsByDate(startDate, endDate) {
    return this.http.get<Array<Event>>('http://localhost:8080/event/?startDate=' + startDate + '&endDate=' + endDate);
  }

  getCategories() {
    return this.http.get('http://localhost:8080/category');
  }

  send(event) {
    console.log(event.eventDate);
    this.http.post<Event>('http://localhost:8080/event/', event, this.httpOptions).subscribe();
  }
}
