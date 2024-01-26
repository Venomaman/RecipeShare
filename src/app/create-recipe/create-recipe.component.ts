import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import { RecipeServiceService } from '../services/recipe/recipe-service.service';

@Component({
  selector: 'app-create-recipe',
  standalone: true,
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatRadioModule
  ],
  templateUrl: './create-recipe.component.html',
  styleUrl: './create-recipe.component.scss'
})
export class CreateRecipeComponent {
  recipeItem:any={
    title:"",
    description:"",
    foodType:"",
    image:""
  }
  constructor(private recipeService:RecipeServiceService){}

  onSubmit(){
    console.log("value:",this.recipeItem)
    this.recipeService.createRecipes(this.recipeItem).subscribe(
      {
        next:data=>console.log("created recipe",data),
        error:error=>console.log("error",error)
      }
    )

  }

}
