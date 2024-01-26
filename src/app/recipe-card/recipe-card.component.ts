import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import { MatDialog } from '@angular/material/dialog';
import { UpdateRecipeComponent } from '../update-recipe/update-recipe.component';

@Component({
  selector: 'app-recipe-card',
  standalone: true,
  imports: [
     MatCardModule,
     MatButtonModule,
     MatDividerModule,
    MatIconModule
    ],
  templateUrl: './recipe-card.component.html',
  styleUrl: './recipe-card.component.scss'
})
export class RecipeCardComponent {

  @Input() recipe:any
  constructor(public dialog:MatDialog){}
  
  handleOpenUpdateRecipeForm(){
    this.dialog.open(UpdateRecipeComponent)
  }

}
