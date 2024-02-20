import { Injectable } from '@angular/core';
import { Pageable } from '../core/model/page/Pageable';
import { Observable, of } from 'rxjs';
import { LoanPage } from './model/LoanPage';
import { LOANS_DATA } from './model/mocker-loans';
import { Loan } from './model/Loan';
import { HttpClient } from '@angular/common/http';
import { LoanFilter } from './model/LoanFilter';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  constructor(
    private http : HttpClient
  ) {}

  getLoans(pageable: Pageable, loanFilter : LoanFilter  ) : Observable<LoanPage>{

    return this.http.post<LoanPage>('http://localhost:8080/loan',{pageable : pageable , filteredLoan : loanFilter});
  }

  saveLoan(loan : Loan) : Observable <void>{
    let url = 'http://localhost:8080/loan';
       return this.http.put<void>(url, loan);
  }

  deleteLoan(idLoan : number) : Observable <void>{
    return this.http.delete<void>('http://localhost:8080/loan/'+idLoan);
  }
}
