package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        app.get("/articles/build", ctx -> {
            var page = new BuildArticlePage();
            ctx.render("/articles/build.jte", model("page", page));
        });

        app.post("/articles", ctx -> {
            String title = "";
            String content = "";
            try {
                title = ctx.formParamAsClass("title", String.class)
                        .check(value -> value.length() >= 2, "Статья должна быть не короче 10 символов")
                        .get();

                content = ctx.formParamAsClass("content", String.class)
                        .check(value -> value.length() >= 10, "У пароля большая длина")
                        .check(value -> !ArticleRepository.existsByTitle(value),
                                "Статья с таким названием уже существует")
                        .get();

                Article page = new Article(title, content);
                ArticleRepository.save(page);
                ctx.render("/articles");
            } catch (ValidationException e) {
                BuildArticlePage page = new BuildArticlePage(title, content, e.getErrors());
                ctx.render("/articles/build.jte", model("page", page));
            }
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
