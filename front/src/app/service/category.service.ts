import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Category} from '../components/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  getCategories() {
    return this.http.get<Array<Category>>('http://localhost:8080/category');
  }

  getCategory(event) {
    return this.http.get<Category>('http://localhost:8080/category/' + event);
  }

  createCategory(category) {
    return this.http.post('http://localhost:8080/category/add', category, this.httpOptions).subscribe();
  }

  remove(id) {
    return this.http.post('http://localhost:8080/category/delete/' + id, this.httpOptions).subscribe();
  }

  edit(cat) {
    return this.http.post('http://localhost:8080/category/edit/' + cat.categoryId, cat, this.httpOptions).subscribe();
  }
}
