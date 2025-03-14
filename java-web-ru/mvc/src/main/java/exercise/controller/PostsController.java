package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.EditPostPage;
import exercise.util.NamedRoutes;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                .get();

            var body = ctx.formParamAsClass("body", String.class)
                .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                .get();

            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/build.jte", model("page", page)).status(422);
        }
    }

    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        ctx.render("posts/index.jte", model("page", postPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    public static void edit(Context ctx) {
        Long id = ctx.queryParamAsClass("id", Long.class).get();
            Post page = PostRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Пост не найден"));
            ctx.render("posts/edit.jte", model("page", page));
    }

//Реализуйте контроллер с методами, необходимыми для вывода формы редактирования и обновления поста.
//
//При обновлении поста, данные от пользователей должны пройти валидацию по тем же правилам, что и при создании.
//Если данные при редактировании не прошли валидацию, должна отобразиться форма редактирования с полями,
//заполненными данными.
//При успешном обновлении нужно сделать редирект на страницу списка постов.

    public static void update(Context ctx) {
        try {
            String name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                    .get();

            String body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                    .get();

            var post = PostRepository.find(ctx.pathParamAsClass("{id}", Long.class).get());
            post.get().setName(name);
            post.get().setBody(body);
            ctx.redirect("posts/" + ctx.pathParamAsClass("{id}", String.class));

        } catch (ValidationException e) {
            String name = ctx.formParam("name");
            String body = ctx.formParam("body");
            Long id = ctx.pathParamAsClass("{id}", Long.class).get();
            BuildPostPage page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/" + id, model("page", page));
        }
    }
}
