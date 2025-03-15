package exercise;

import io.javalin.Javalin;
import exercise.controller.SessionsController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

       app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
       app.post(NamedRoutes.loginPath(), SessionsController::login);
       app.post(NamedRoutes.logoutPath(), SessionsController::logout);

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

//Опишите роутинг для указанных выше обработчиков. Используйте именованные маршруты.
//
//В этом задании имена маршрутов уже описаны в классе NamedRoutes.

//GET /sessions/build — форма логина, в которой пользователь вводит имя и пароль
//POST /sessions для создания сессии
//POST /sessions/delete для удаления сессии
//
//public static String rootPath() {
//    return "/";
//}
//public static String loginPath() {
//    return "/sessions";
//}
//public static String logoutPath() {
//    return "sessions/delete";
//}
//public static String buildSessionPath() {
//    return "/sessions/build";
//}