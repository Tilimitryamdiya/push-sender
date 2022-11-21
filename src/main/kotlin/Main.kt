import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {

    val action = "action"
    val content = "content"

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val newLike = Message.builder()
        .putData(action, "LIKE")
        .putData(
            content, """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken(TOKEN)
        .build()

    val newPost = Message.builder()
        .putData(action, "NEW_POST")
        .putData(
            content, """{
          "userId": 1,
          "userName": "Android Developers",
          "content": "A basic notification usually includes a title, a line of text, and one or more actions the user can perform in response. To provide even more information, you can also create large, expandable notifications by applying one of several notification templates as described on this page."
        }""".trimIndent()
        )
        .setToken(TOKEN)
        .build()

    val update = Message.builder()
        .putData(action, "UPDATE")
        .putData(
            content, """{
          "userId": 1,
          "userName": "Vasiliy"
        }""".trimIndent()
        )
        .setToken(TOKEN)
        .build()

    FirebaseMessaging.getInstance().send(newLike)
    FirebaseMessaging.getInstance().send(newPost)
    FirebaseMessaging.getInstance().send(update)
}