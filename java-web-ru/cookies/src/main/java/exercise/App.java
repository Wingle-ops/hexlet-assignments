package exercise;

import io.javalin.Javalin;
import exercise.controller.UsersController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.post(NamedRoutes.userPath("{id}"), UsersController::reg);

        app.get(NamedRoutes.userPath("{id}"), UsersController::show);

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

//Добавьте в приложение роутинг для этих маршрутов.

//Обработчик POST /users для регистрации пользователя.
// Он должен принимать данные формы и устанавливать специальный токен в куке.
//Для генерации токена используйте метод Security.generateToken(). Пользователь должен сохраняться в репозитории.
//Затем должен происходить редирект на /users/{id}, где {id} — это id зарегистрированного пользователя
//
//Обработчик GET /users/{id}. Он должен отображать данные пользователя на странице, но только в том случае,
//если токен пользователя в куке совпадает с токеном пользователя, id которого указан в адресе.
//Для поиска пользователя используйте метод find() из класса репозитория.
//Затем обработчик должен отрисовывать шаблон users/show.jte с данными пользователя.
//Если пользователь не совпадает по токену, должен происходить редирект на /users/build
