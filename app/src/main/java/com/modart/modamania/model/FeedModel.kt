package com.modart.modamania.model

/**
 * posts": {
"owner": {
"user_folder": "default.jpeg",
"_id": "5d0e0d93c7bb8100344fbbce",
"username": "meksconway",
"name_surname": "Emin Kişi"
},
"likeCount": 0,
"isTrending": false,
"likeStatus": false,
"comment_count": 0,
"viewed_count": 0,
"created_date": "2019-06-23T12:14:56.542Z",
"post_image": "https://modamaniaapp.s3.amazonaws.com/1561293718387",
"_id": "5d0f7397f39f5f00346ea727",
"description": "4. post",
"likes": [],
"comments": [],
"viewed_people": []
}
 * */


data class FeedModel(

    val message: String?,
    val id: String?,
    val posts: FeedPostModel
)


data class FeedPostModel(

    val owner: FeedOwnerModel,
    val likeCount: Int,
    val comment_count: Int,
    val viewed_count: Int,
    val created_date: String,
    val post_image: String,
    val _id: String,
    val description: String
    //lvc yapılcak


)

data class FeedOwnerModel(

    val user_folder: String,
    val _id: String,
    val username: String,
    val name_surname: String

)

