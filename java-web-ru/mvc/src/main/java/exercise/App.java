package exercise;

import io.javalin.Javalin;
import exercise.controller.PostsController;
import exercise.controller.RootController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        app.get(NamedRoutes.buildPostPath(), PostsController::build);
        app.post(NamedRoutes.postsPath(), PostsController::create);

        app.get(NamedRoutes.postsPath(), PostsController::index);
        app.get(NamedRoutes.postPath("{id}"), PostsController::show);

        app.post(NamedRoutes.postPath("{id}"), PostsController::update);

        return app;
    }

//    Добавьте в приложение роутинг для редактирования постов.
//
//    Обычно обновление сущности принято делать с помощью глаголов PATCH/PUT.
//    Но здесь ситуация другая — мы не можем отправлять PATCH/PUT запросы из HTML-формы.
//    Поэтому в этом упражнении сделайте обновление сущности через POST-запрос на адрес /posts/{id}.
//    Так обновление будет работать в браузере.

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}


