package com.example.livechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.livechat.Screens.LoginScreen
import com.example.livechat.ui.theme.LiveChatTheme
import androidx.navigation.compose.composable
import com.example.livechat.Screens.SignUpScreen
import androidx.hilt.navigation.compose.hiltViewModel



sealed class DestinationScreen(var route: String){
    object SignUp : DestinationScreen( "signup")
    object Login : DestinationScreen( "login")
    object Profile : DestinationScreen( "profile")
    object ChatList : DestinationScreen( "chatList")
    object SingleChat : DestinationScreen( "singleChat/{chatId}"){
        fun createRoute(id : String) = "singleChat/$id"
    }
    object StatusList : DestinationScreen( "statusList")
    object SingleStatus : DestinationScreen( "singleStatus/{statusId}"){
        fun createRoute(userId : String) = "singleStatus/$userId"
    }
}





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatAppNavigation()

                }
            }
        }
    }
    @Composable
    fun ChatAppNavigation(){

        val navController = rememberNavController()
        var vm=hiltViewModel<LCViewModel>()
        NavHost(navController = navController , startDestination = DestinationScreen.SignUp.route){
            composable(DestinationScreen.SignUp.route){

                SignUpScreen(navController,vm)
            }

            composable(DestinationScreen.Login.route){

                LoginScreen()
            }


        }


    }


}