import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { RecipeCardComponent } from './recipe-card/recipe-card.component';
import { HomeComponent } from './home/home.component';
import { AuthComponent } from './auth/auth.component';
import { AuthServiceService } from './services/auth/auth-service.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    NavbarComponent,
    FooterComponent,
    RecipeCardComponent,
    HomeComponent,
    AuthComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
  user:any=null;

  constructor(public authService:AuthServiceService){}
  ngOnInit(){
    // console.log("ngonit")
    this.authService.getUserProfile().subscribe({
      next:data=>console.log("req user",data),
      error:error=>console.log("error",error)
    });
    this.authService.authSubject.subscribe(
      (auth)=>{
        // console.log("auth object value",auth)
        this.user=auth.user
      }
    )
  }
}
