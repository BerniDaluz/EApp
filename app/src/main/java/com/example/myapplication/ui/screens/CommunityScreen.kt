package com.example.myapplication.ui.screens


import androidx.compose.runtime.Composable

//import layout components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

//import color + styling
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//import custom theme
import com.example.myapplication.ui.theme.EnvironmentalColors

// communityscreen shows community posts and discussions
//list of com posts from users
//each post user name , content, likes , comments
//button to make new posts
//user can interact with post

@Composable
fun CommunityScreen(){
    //arrange items vertically t-b
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            "Community Feed",
            color = EnivronmentalColors.TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        //create post button //
        Button(
            onClick = {
                //navigate to create post

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Text(
                "+ Create Post",
                color = EnvironmentalColors.TextPrimary,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        ///post list ///
        val samplePosts = listOf(
            //post1
            Post(
                id = "1",
                userName = "John Smith",
                content = "Great air quality today! AQi is at 45 in downtown area.",
                likes = 12,
                comments = 3
            ),

            //2
            Post(
                id = "2",
                userName = "jane Doe",
                content = "High PM2.5 levels detected in downtown. Stay safe!",
                likes = 8,
                comments = 5
            ),

            //3
            Post(
                id = "3",
                userName = "Mike Johnson",
                content = "Just recorded new environmental data at the park. Humidity is 65%!",
                likes = 15,
                comments = 2
            ),

            //4
            Post(
                id = "4",
                userName = "Sarah Williams",
                content = "CO2 levels are increasing, We need to take action!",
                likes = 20,
                comments = 8
            )

        )

        //scroll list renders visible items
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            //loop items through each post + display it
            items(samplePosts) {
                post ->
                //display each post as card
                PostCard(post = post)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

    }

}
//postcard singe community post displayed as card
@Composable
fun PostCard(post: Post){
    //raise container with shadow
    Card(
        modifier = Modifier
            .fillMaxWidth(),
            .padding(8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        //round corners
        colors = CardDefaults.cardColors(
            containerColor = EnvironmentalColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ){
        //arrange content vertically
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            ///user Name ///
            Text(
                post.userName,
                color = EnvironmentalColors.TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            ///Post Content ///
            Text(
                post.content,
                color = EnvironmentalColors.TextSecondary,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            ///interaction row ///
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)

                ){
                    //heart icon emoji
                    Text(
                        "❤\uFE0F",
                        fontSize = 16.sp
                    )
                    //like count
                    Text(
                        "${post.likes}",
                        color = EnvironmentalColors.Text.Tertiary,
                        fontSize = 12.sp
                    )
                }

                /// commemts///
                Row (
                    verticalAlignment =  Alignment.CenterVertically,
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)
                )
                {
                    Text(
                        //comment icon emoji
                        "\uD83D\uDCAC",
                        fontSize = 16.sp,
                    )
                    //comment count
                    Text(
                        "${post.comments}",
                        color = EnvironmentalColors.TextTertiary,
                        fontSize =  12.sp
                    )
                }
            }
        }
    }
}

//data class representing community post
data class Post(
    val id: String,//unique ID
    val userName: String,//who posted it
    val content: String, //what they said
    val likes: Int, // how many likes
    val comments: Int //how many comments
)





