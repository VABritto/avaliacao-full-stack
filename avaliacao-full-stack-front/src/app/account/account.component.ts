import { Component, OnInit } from '@angular/core';
import { Account } from '../common/account';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  account: Account = new Account(0.0, "000000", "N/A");

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {    
    this.getAccount();
  }
  getAccount() {
    this.accountService.getAccountByNumber("000001").subscribe(
      data => {
        this.account = data;
      }
    );
  }

}
