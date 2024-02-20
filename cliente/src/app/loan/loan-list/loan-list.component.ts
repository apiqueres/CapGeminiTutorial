import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/client/model/Client';
import { Game } from 'src/app/game/model/Game';
import { GameService } from 'src/app/game/game.service';
import { ClientService } from 'src/app/client/client.service';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { LoanService } from '../loan.service';
import { Loan } from '../model/Loan';
import { MatTableDataSource } from '@angular/material/table';
import { LoanEditComponent } from '../loan-edit/loan-edit.component';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { LoanFilter } from '../model/LoanFilter';

@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.component.html',
  styleUrls: ['./loan-list.component.scss']
})
export class LoanListComponent implements OnInit{
  
  pageNumber: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;
  games: Game[];
  clients : Client[];
  loanFilter : LoanFilter;

  dataSource = new MatTableDataSource<Loan>();
  displayedColumns: string[] = ['id', 'game', 'client', 'initDate', 'finDate'];
loan: any;

  constructor(
    private loanService : LoanService,
    private gameService : GameService,
    private clientService : ClientService,
    public dialog: MatDialog
  ){
    this.loanFilter = new LoanFilter();
  }

  ngOnInit(): void {

    this.loadPage();
    this.gameService.getGames().subscribe(games => this.games = games);
    this.clientService.getClients().subscribe(clients => this.clients = clients);

  }

  loadPage(event?: PageEvent) {

    let pageable : Pageable =  {
        pageNumber: this.pageNumber,
        pageSize: this.pageSize,
        sort: [{
            property: 'id',
            direction: 'ASC'
        }]
    }
   

    if (event != null) {
        pageable.pageSize = event.pageSize
        pageable.pageNumber = event.pageIndex;
    }

    if(this.loanFilter.filteredDate!=null){
      const date : Date = new Date(this.loanFilter.filteredDate);
      date.setDate(date.getDate() + 1);
      this.loanFilter.filteredDate = date;
    }
    

    
    this.loanService.getLoans(pageable, this.loanFilter).subscribe( data => {

      this.dataSource.data = data.content;
        this.pageNumber = data.pageable.pageNumber;
        this.pageSize = data.pageable.pageSize;
        this.totalElements = data.totalElements;

    });
  }
  
  onCleanFilter():void{
    this.loanFilter = new LoanFilter();

    this.loadPage();
  }

  
   createLoan(){
    const dialogRef = this.dialog.open( LoanEditComponent, {
      data: {}
    });
    
    dialogRef.afterClosed().subscribe( result => { 
      this.ngOnInit()
    });
  }
  
  editLoan(loan : Loan){

    const dialogRef = this.dialog.open(LoanEditComponent, {
      data : {loan : loan}
      });

      dialogRef.afterClosed().subscribe(result => {
        this.ngOnInit();
    });
  }

  deleteLoan( loan : Loan) {    
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
        data: { title: "Eliminar préstamo", description: "Atención si borra el préstamo se perderán sus datos.<br> ¿Desea eliminar el préstamo?" }
    });

    dialogRef.afterClosed().subscribe(result => {
        if (result) {
            this.loanService.deleteLoan(loan.id).subscribe(result =>  {
                this.ngOnInit();
            }); 
        }
    });
} 

}
