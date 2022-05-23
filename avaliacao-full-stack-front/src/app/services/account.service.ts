import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Account } from '../common/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseURL = 'http://localhost:8080/api/account';
  

  constructor(private httpClient: HttpClient) { }

  getAccountByNumber(accountNumber: string): Observable<Account> {
    return this.httpClient.get<any>(
      this.baseURL + '/account-sum/' + accountNumber,
      {observe: 'body', responseType: 'json'}
    )
    .pipe(
      map( (data: Account) => {
        return data;
      })
    );
  }
}