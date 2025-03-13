package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static void index(Context ctx) {
        int id = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int count = 5;
        var list = PostRepository.findAll(id, count);
        PostsPage page = new PostsPage(list, id);
        ctx.render("/posts/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        Long id = ctx.pathParamAsClass("{id}", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Такой страницы не существует"));
        PostPage page = new PostPage(post);
        ctx.render("/posts/show.jte", model("page", page));
    }
}
