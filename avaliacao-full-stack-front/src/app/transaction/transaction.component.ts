import { Component, OnInit } from '@angular/core';
import { Message } from 'primeng/api';
import { Transaction } from '../common/transaction';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  transactions: Transaction[] = [];
  display: boolean = false;
  transaction: Transaction = new Transaction();
  feeTypes: string[] = ['A', 'B', 'C', 'D'];
  msgsDialog: Message[] = [];
  msgsSuccess: Message[] = [];
  

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {    
    this.listTransactions();
  }
  listTransactions() {
    this.transactionService.getTransactionList("000001").subscribe(
      data => {
        this.transactions = data;
      }
    );
  }
  toggleDialog() {
    this.display = !this.display;
  }
  scheduleTransfer() {
    this.transaction.depositorAccount = '000001';
    let validated = this.validateFields();
    if (validated) {
      this.transactionService.makeTransaction(this.transaction).subscribe(
        data => {
          this.transaction = data;
        }
      );
      this.toggleDialog();
      this.listTransactions();
      this.msgsSuccess = [{severity: 'success', summary: 'Successo', detail: 'Transferência feita!'}]
    }
  }
  validateFields(): boolean {
    if(this.transaction.amount == null || this.transaction.amount <= 0 || this.transaction.receiverAccount == null || this.transaction.receiverAccount == '000000' || this.transaction.receiverAccount == this.transaction.depositorAccount || this.transaction.transferDate == null || this.transaction.transferDate <= this._getYesterdayDate()) {
      this.msgsDialog = [{severity: 'warn', summary: 'Aviso', detail: 'Todos os campos devem ser preenchidos e a data de transferência deve ser para hoje em diante.'}]
      return false;
    }
    return true;
  }
  cancelTransfer() {
    this.transaction = new Transaction();
    this.toggleDialog();
  }
  private _getYesterdayDate(): Date{
    const yesterday:Date = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    return yesterday;
  }
}
