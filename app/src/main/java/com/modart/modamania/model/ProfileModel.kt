package com.modart.modamania.model



data class ProfileModel (

    val user: ProfileUserModel

)

data class ProfileUserModel(

    val user_folder: String,
    val following_count: Int,
    val follower_count: Int,
    val total_post_count: Int,
    val _id: String,
    val username: String,
    val name_surname: String,
    val posts: List<FeedPostModel>,
    val followers: List<FollowingModel>,
    val following: List<FollowingModel>

)

data class FollowingModel(
    val user_folder: String,
    val _id: String,
    val usernameX: String,
    val name_surname: String
)


/*

 {
 "user": {
 "user_folder": "default.jpeg",
 "following_count": 0,
 "follower_count": 0,
 "total_post_count": 8,
 "is_trending": 0,
 "total_like": 0,
 "total_viewed": 0,
 "gender": 1,
 "_id": "5d0e0d93c7bb8100344fbbce",
 "username": "meksconway",
 "name_surname": "Emin Kişi",
 "followers": [],
 "following": [],
 "posts": [
 {
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
 "created_date": "2019-06-23T11:02:02.422Z",
 "post_image": "https://modamaniaapp.s3.amazonaws.com/1561287743052",
 "likes": [],
 "comments": [],
 "viewed_people": [],
 "_id": "5d0f5c406a99720034b96256",
 "description": "Merhaba ilk post! #helloworld"
 },
 {
 "owner": {
 "user_folder": "default.jpeg",
 "_id": "5d0e0d93c7bb8100344fbbce",
 "username": "meksconway",
 "name_surname": "Emin Kişi"
 }
 ],
 "__v": 10
 }
 }
 */

