package eu.tutorials.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel :ViewModel (){
    private  val _categoryState= mutableStateOf(RecipeState()) // mutableStateOf() fonksiyonu ile RecipeState sınıfından bir nesne oluşturuyoruz ve bu nesneyi _categoryState değişkenine atıyoruz
    val categoryState: State<RecipeState> = _categoryState // _categoryState değişkenini yalnızca okunabilir bir State olarak dışarıya sunuyoruz
 init {
     fetchCategories() // fetchCategories() fonksiyonunu çağırıyoruz
 }

private  fun fetchCategories(){ // fetchCategories adında bir fonksiyon oluşturuyoruz
viewModelScope.launch {

    try {

 val response= repiceservice.getCategories() // repiceservice değişkeni ile getCategories() fonksiyonunu çağırıyoruz ve dönen değeri response değişkenine atıyoruz
        _categoryState.value=_categoryState.value.copy( // _categoryState değişkeninin değerini değiştiriyoruz
         recipeList =  response.categories,  // recipeList değerine response.categories değerini atıyoruz
            Loading = false,// isLoading değerini false yapıyoruz
            error = null // error değerini null yapıyoruz


             // recipeList değerine response.categories değerini atıyoruz
        )

    }
    catch (e:Exception){ // Hata durumunda
        _categoryState.value=_categoryState.value.copy( // _categoryState değişkeninin değerini değiştiriyoruz
            Loading = false, // isLoading değerini false yapıyoruz
            error = "Error Fetching Categories ${e.message}" // error değerine hata mesajını atıyoruz


        )
    }



}

}


    data class  RecipeState( // RecipeState adında bir data class oluşturuyoruz
        val Loading:Boolean=true, // default value is true
        val recipeList:List<Category> = emptyList(),  // default value is emptyList()
        val error:String?=null // default value is null
    )
}