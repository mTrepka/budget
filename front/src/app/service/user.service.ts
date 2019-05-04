import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../components/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  getUser() {
    return this.http.get<User>('http://localhost:8080/user');
  }

  update(user) {
    return this.http.post('http://localhost:8080/user/edit/', user, this.httpOptions);
  }
}
