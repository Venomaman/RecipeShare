import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthServiceService } from '../services/auth/auth-service.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [ 
    MatToolbarModule,
    MatButtonModule,
    MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  user:any=null;
  constructor(public authService:AuthServiceService,private router:Router){}
  ngOnInit(){
    this.authService.authSubject.subscribe(
      (auth)=>{
        // console.log("auth object value",auth)
        this.user=auth.user
      }
    )
  }

   handelLogout(){
    this.authService.logout()
  }

}
