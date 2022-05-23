import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { TransactionComponent } from './transaction/transaction.component';

import { HttpClientModule } from '@angular/common/http';
import { TransactionService } from './services/transaction.service';
import { ButtonModule } from "primeng/button";
import {MenubarModule} from 'primeng/menubar';
import { AppRoutingModule } from './app-routing.module';
import { AccountComponent } from './account/account.component';
import { AccountService } from './services/account.service';
import {PanelModule} from 'primeng/panel';
import {DialogModule} from 'primeng/dialog';
import {DropdownModule} from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {InputNumberModule} from 'primeng/inputnumber';
import {InputMaskModule} from 'primeng/inputmask';
import {CalendarModule} from 'primeng/calendar';


@NgModule({
  declarations: [
    AppComponent,
    TransactionComponent,
    AccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ButtonModule,
    MenubarModule,
    PanelModule,
    DialogModule,
    DropdownModule,
    FormsModule,
    MessagesModule,
    MessageModule,
    InputNumberModule,
    InputMaskModule,
    CalendarModule
  ],
  providers: [TransactionService, AccountService],
  bootstrap: [AppComponent]
})
export class AppModule { }
