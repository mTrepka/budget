import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../service/category.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {
  categories;
  events;
  cat;
  newCat;
  current;
  rm = false;
  ed = false;
  sh = false;
  nc = false;
  result = 'Result';

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.cat = {
      categoryId: 0,
      name: '',
      eventList: null
    };
    this.newCat = {
      categoryId: 0,
      name: '',
      eventList: null
    };
    this.categoryService.getCategories().subscribe(e => this.categories = e);
  }

  newCategory() {
    this.close();
    this.nc = true;
  }

  remove(event) {
    this.current = event;
    this.close();
    this.rm = true;
  }

  rem() {
    this.categoryService.remove(this.current);
    window.location.reload();
  }

  edit(event) {
    this.close();
    this.ed = true;
    this.cat = this.categories.filter(e => e.categoryId === event)[0];
  }

  show(event) {
    close();
    this.sh = true;
    this.categoryService.getCategory(event).subscribe(e => {
      this.events = e.eventList;
      this.events.forEach(b => b.eventDate = new Date(b.eventDate).toDateString());
    });
  }

  close() {
    this.rm = false;
    this.ed = false;
    this.sh = false;
    this.nc = false;
  }

  change() {
    this.categoryService.edit(this.cat);
    window.location.reload();
  }

  sendNew() {
    this.categoryService.createCategory(this.newCat);
    window.location.reload();
  }
}
