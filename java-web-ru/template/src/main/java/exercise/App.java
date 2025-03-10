package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/users", ctx -> {
            UsersPage page = new UsersPage(USERS);
            ctx.render("users/index.jte", model("page", page));
        });

        app.get("users/{id}", ctx -> {
            Long id = ctx.queryParamAsClass("id", Long.class).get();
            User user = USERS.stream()
                    .filter(u -> id.equals(u.getId()))
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                throw new NotFoundResponse("User not found");
            }

            UserPage page = new UserPage(user);
            ctx.render("users/index.jte", model("page", page));
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

//В приложении реализуйте два обработчика:
//
//Для вывода списка пользователей /users
//Для вывода конкретного пользователя /users/{id}
//
//Список пользователей List<User> находится в константе USERS.
//
//Если пользователь с таким идентификатором не существует, приложение должно вернуть страницу с HTTP-кодом 404 и текстом User not found.