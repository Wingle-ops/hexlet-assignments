package exercise;

import io.javalin.Javalin;
import java.util.List;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            String firstname = StringUtils.capitalize(ctx.formParam("firstName"));
            String lastname = StringUtils.capitalize(ctx.formParam("lastName"));
            String email = ctx.formParam("email").trim().toLowerCase();
            String password = Security.encrypt(ctx.formParam("password"));

            User user = new User(firstname, lastname, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

//Имя и фамилию пользователя нужно капитализировать
//Электронную почту нужно привести к нижнему регистру и удалить концевые пробелы
//
//В целях безопасности мы должны хранить пароль пользователя в репозитории в зашифрованном виде.
//Перед сохранением пароля зашифруйте его с помощью уже созданного метода Security.encrypt().
