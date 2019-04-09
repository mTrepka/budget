<<<<<<< HEAD
import { Injectable } from '@angular/core';
=======
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpHeaders} from '@angular/common/http';
>>>>>>> origin/master

@Injectable({
  providedIn: 'root'
})
<<<<<<< HEAD
export class EventService {
  EXAMPLE_DATA = [
    {mName: 'Paliwo', type: 'w', category: {name: 'samochod'}, value: 50, data: '2019-03-01'},
    {mName: 'Naprawa komputera', type: 'p', category: {name: 'praca'}, value: 150, data: '2019-03-02'},
    {mName: 'Zakup opony', type: 'w', category: {name: 'samochod'}, value: 70, data: '2019-03-03'},
    {mName: 'Wymiana oleju', type: 'w', category: {name: 'samochod'}, value: 60, data: '2019-03-04'},
    {mName: 'Wymiana karty graficznej', type: 'p', category: {name: 'praca'}, value: 40, data: '2019-03-05'}
  ];
  constructor() { }

  getEvents() {
    return this.EXAMPLE_DATA;
=======


export class EventService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  getEvents() {
    return this.http.get<Event>('http://localhost:8080/event/');
  }

  getEventsByDate(startDate, endDate) {
    return this.http.get<Event>('http://localhost:8080/event/?startDate=' + startDate + '&endDate=' + endDate);
  }

  getCategories() {
    return this.http.get('http://localhost:8080/category');
  }

  send(event) {
    this.http.post<Event>('http://localhost:8080/event/', event, this.httpOptions).subscribe();
>>>>>>> origin/master
  }
}
