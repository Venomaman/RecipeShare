import { Component } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatTabGroup, MatTab, MatTabsModule } from '@angular/material/tabs';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatInput, MatInputModule } from '@angular/material/input';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { AuthServiceService } from '../services/auth/auth-service.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    MatTabsModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss'
})
export class AuthComponent {

constructor(public authService:AuthServiceService){}

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  signupForm = new FormGroup({
    fullName: new FormControl(''),
    password: new FormControl(''),
    email: new FormControl('')
  });

  onLogin() {
    // Handle login logic here
    // if (this.loginForm.valid) {
    //   const { email, password } = this.loginForm.value;
    //   // Use the email and password to log in.
    //   // This is where you'd call your login service.
    //   console.log(`Logging in with email: ${email} and password: ${password}`);
    // } else {
    //   console.log('Login form is not valid');
    // }
    this.authService.login(this.loginForm.value).subscribe({
      next:(response)=>{
        localStorage.setItem("jwt",response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("login success", response)
      }
    })
  }

  onSignup() {
    // Handle signup logic here
    // if (this.signupForm.valid) {
    //   const { fullname, email, password } = this.signupForm.value;
    //   // Use the fullname, email, and password to sign up
    //   // This is where you'd call your signup service.
    //   console.log(`Signing up with fullname: ${fullname}, email: ${email}, and password: ${password}`);
    // } else {
    //   console.log('Signup form is not valid');
    // }
    this.authService.signup(this.signupForm.value).subscribe({
      next:(response)=>{
        localStorage.setItem("jwt",response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("signup success", response)
      }
    })
  }

  isLoginTab =true;

  toggle():void{
      this.isLoginTab = !this.isLoginTab;
    }      
}
