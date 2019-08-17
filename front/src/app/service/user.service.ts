import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../components/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
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

  registration(user) {
    return this.http.post('http://localhost:8080/register', user, this.httpOptions);
  }

  userExistByEmail(email) {
    return this.http.get<boolean>('http://localhost:8080/email/?email=' + email);
  }

  userExistByUsername(username) {
    return this.http.get<boolean>('http://localhost:8080/username/?username=' + username);
  }

  getUserSecuritylog() {
    return this.http.get('http://localhost:8080/user/security/log/');
  }

  getUsernameAndToken() {
    return this.http.get('http://localhost:8080/user/info-token');
  }
}
