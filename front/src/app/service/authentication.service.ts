import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<string>;
  public currentUser: Observable<string>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<string>(localStorage.getItem('currentUser'));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): string {
    return this.currentUserSubject.getValue();
  }

  login(user: string, pass: string) {

    const u = {
      username: user,
      password: pass
    };
    return this.http.post<any>('http://localhost:8080/login', u, {observe: 'response'})
      .pipe(map(resp => {
        if (resp && resp.headers.get('Authorization')) {
          this.currentUserSubject.next(user);
          localStorage.setItem('currentUser', user);
          localStorage.setItem('token', resp.headers.get('Authorization'));
        }
        return resp;
      }));
  }

  logout() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
    this.currentUserSubject.next(null);
  }

}
