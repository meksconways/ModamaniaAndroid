package com.modart.modamania.model

/****
 * /*
{
"user": {
"name_surname": "Emin Kişi",
"username": "meksconway",
"user_id": "5d0e0d93c7bb8100344fbbce"
},
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im11aGFtbWVkZW1pbmtpc2lAZ21haWwuY29tIiwidXNlcl9pZCI6IjVkMGUwZDkzYzdiYjgxMDAzNDRmYmJjZSIsInVzZXJuYW1lIjoibWVrc2NvbndheSIsInVzZXJfZm9sZGVyIjoiZGVmYXVsdC5qcGVnIiwibmFtZV9zdXJuYW1lIjoiRW1pbiBLacWfaSIsImlhdCI6MTU2MTI4NjAwNSwiZXhwIjoxNTY2NDcwMDA1fQ.v2uOMwpyTyf0oWB7JJ0yJf51HVx_vqCWQgwa0U0TsIo"
}
*/
 * */


data class LoginModel (

   val user: LoginUserModel,
   val token: String

)
data class LoginUserModel(

    val name_surname: String,
    val username: String,
    val user_id: String


)