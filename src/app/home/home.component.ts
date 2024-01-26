import { Component } from '@angular/core';
import { RecipeCardComponent } from '../recipe-card/recipe-card.component';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import { CreateRecipeComponent } from '../create-recipe/create-recipe.component';
import { AuthServiceService } from '../services/auth/auth-service.service';
import { RecipeServiceService } from '../services/recipe/recipe-service.service';
import { state } from '@angular/animations';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RecipeCardComponent,MatIconModule,MatButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  recipes= [];
  constructor(public dialog:MatDialog,
     public authService:AuthServiceService,
     private recipeService:RecipeServiceService
     ){}

  OpenCreateRecipeForm(){
    this.dialog.open(CreateRecipeComponent)
  }

 
  ngOnInit() {
    this.authService.getUserProfile();
    this.recipeService.getRecipes().subscribe()
    this.recipeService.recipeSubject.subscribe(
      (state)=>{
        this.recipes=state.recipes
      }
    )
  }

}
