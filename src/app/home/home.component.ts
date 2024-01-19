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

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RecipeCardComponent,MatIconModule,MatButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor(public dialog:MatDialog){}
  OpenCreateRecipeForm(){
    this.dialog.open(CreateRecipeComponent)
  }

  recipe = [1,1,1,2,2,3]

}
