package application

import application.configs.mysqlConnection
import domain.*
import io.javalin.Javalin
import mongoDBConnection
import java.time.LocalDateTime

fun main(args: Array<String>) {
    println("Starting forum service!")
    mysqlConnection()
    mongoDBConnection("agora")

    val app = Javalin.create().start(7000)

    val users = mutableListOf<User>()
    val articles = mutableListOf<Article>()
    val likes = mutableListOf<Like>()
    val followers = mutableListOf<Follower>()
    val comments = mutableListOf<Comment>()

    app.post("/users") { ctx ->
        val user = ctx.bodyAsClass(User::class.java)
        users.add(user)
        ctx.status(201)
        ctx.json(user)
    }

    app.post("/articles") { ctx ->
        val article = ctx.bodyAsClass(Article::class.java)
        articles.add(article)
        ctx.status(201)
        ctx.json(article)
    }

    app.post("/articles/{articleId}/likes") { ctx ->
        val articleId = ctx.pathParam("articleId")
        val article = articles.find { it.title == articleId }
        val user = ctx.bodyAsClass(User::class.java)
        val like = Like(user, article!!, LocalDateTime.now())
        likes.add(like)
        ctx.status(201)
        ctx.json(like)
    }

    app.post("/users/{username}/followers") { ctx ->
        val username = ctx.pathParam("username")
        val user = users.find { it.username == username }
        val follower = ctx.bodyAsClass(User::class.java)
        val following = user!!
        val follow = Follower(follower, following, LocalDateTime.now())
        followers.add(follow)
        ctx.status(201)
        ctx.json(follow)
    }

    app.post("/articles/{articleId}/comments") { ctx ->
        val articleId = ctx.pathParam("articleId")
        val article = articles.find { it.title == articleId }
        val user = ctx.bodyAsClass(User::class.java)
        val comment = ctx.bodyAsClass(Comment::class.java)
        comment.author = user
        comment.article = article!!
        comment.publicationDate = LocalDateTime.now()
        comments.add(comment)
        ctx.status(201)
        ctx.json(comment)
    }
}