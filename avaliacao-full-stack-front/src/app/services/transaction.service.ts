import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Transaction } from '../common/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseURL = 'http://localhost:8080/api/transaction';
  

  constructor(private httpClient: HttpClient) { }

  getTransactionList(accountNumber: string): Observable<Transaction[]> {
    return this.httpClient.get<any>(
      this.baseURL + '/list-all-transactions/' + accountNumber,
      {observe: 'body', responseType: 'json'}
    )
    .pipe(
      map( (data: Transaction[]) => {
        return data;
      })
    );
  }
  makeTransaction(transaction: Transaction): Observable<Transaction> {
    return this.httpClient.post<Transaction>(this.baseURL + '/make-transaction', transaction);
  }
}