import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
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
  }
}
