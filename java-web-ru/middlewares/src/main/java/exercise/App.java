package exercise;

import io.javalin.Javalin;
import exercise.controller.PostsController;
import exercise.controller.RootController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

import org.apache.commons.codec.digest.DigestUtils;

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

        app.get(NamedRoutes.editPostPath("{id}"), PostsController::edit);
        app.post(NamedRoutes.postPath("{id}"), PostsController::update);

        app.after(ctx -> {
            // Получаем тело ответа
            String responseBody = ctx.path();
            if (!responseBody.isEmpty()) {
                // Вычисляем SHA-256 хеш
                String sha = DigestUtils.sha256Hex(responseBody);
                // Добавляем хеш в заголовок
                ctx.header("X-Response-Digest ", sha);
            }
        });

//        Создайте мидлвару, которая будет вычислять SHA-256 хеш тела ответа и добавлять его
//        в заголовок X-Response-Digest для каждого ответа нашего приложения

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}



