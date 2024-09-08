package eu.tutorials.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@Composable
fun RecipeScreen (modifier: Modifier = Modifier){
    //RecipeScreen adında bir fonksiyon oluşturuyoruz ve bu fonksiyon @Composable anotasyonu ile işaretliyoruz

    val recipeViewModel: MainViewModel= viewModel()// MainViewModel sınıfından bir nesne oluşturuyoruz ve recipeViewModel değişkenine atıyoruz

    val viewState by recipeViewModel.categoryState // recipeViewModel.categoryState değişkenini viewState değişkenine atıyoruz

   Box(modifier = Modifier.fillMaxSize()){

       when{
           viewState.Loading ->{

             // Eğer hata varsa ekrana "Error OCCURRED" yazısını yazdırıyoruz

               }
           viewState.error !=null ->{
               Text("Error OCCURRED")
           }
           else ->{
               CategorScreen(categories = viewState.recipeList) // CategorScreen fonksiyonunu çağırıyoruz ve
           // categories parametresi ile viewState.recipeList değerini gönderiyoruz
           }

       }

   }

}

@Composable
fun  CategorScreen(categories:List<Category>)
{
    
    LazyVerticalGrid(GridCells.Fixed (2),modifier = Modifier.fillMaxSize()){
        items(categories)  // items fonksiyonunu çağırıyoruz ve categories parametresi ile kategorileri listeliyoruz

        {
           category->
            CategoryItem(category = category)
        }
    // LazyVerticalGrid fonksiyonunu çağırıyoruz ve GridCells.Fixed(2) parametresi ile 2 sütunlu bir grid oluşturuyoruz
  }
 }
@Composable
// CategorScreen adında bir fonksiyon oluşturuyoruz ve bu fonksiyon @Composable anotasyonu ile işaretliyoruz
fun CategoryItem(category: Category){
    // CategoryItem adında bir fonksiyon oluşturuyoruz ve bu fonksiyon @Composable anotasyonu ile işaretliyoruz
   Column( modifier = Modifier
       .padding(8.dp)
       .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
       Image(painter= rememberAsyncImagePainter(model = category.strCategoryThumb),
           contentDescription = null, // contentDescription parametresini null yapıyoruz
           modifier= Modifier
               .fillMaxSize()
               .aspectRatio(1f) // aspectRatio(1f) parametresi ile resmin boyutlarını ayarlıyoruz


           )


       // Column fonksiyonunu çağırıyoruz ve padding(16.dp) parametresi ile 16dp padding ekliyoruz
       Text(text = category.strCategory,
           color = Color.Black,
           style = TextStyle(fontWeight = FontWeight.Bold),
           modifier = Modifier.padding(top=4.dp)


       )


       // Text fonksiyonunu çağırıyoruz ve category.strCategory parametresi ile kategori adını yazdırıyoruz

   }
        // Text fonksiyonunu çağırıyoruz ve category.strCategory parametresi ile kategori adını yazdırıyoruz
    }







