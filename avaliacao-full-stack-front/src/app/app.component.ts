import { Component } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'avaliacao-full-stack-front';
  display = true;
  items: MenuItem[] = [];
  ngOnInit() {
    this.items = [
      {label: 'Conta', routerLink: ['']},
      {label: 'Transferências', routerLink: ['/transaction']}
    ];
  }
  
}
