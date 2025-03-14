package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    public static void create(Context ctx) throws Exception {
        var firstName = StringUtils.capitalize(ctx.formParam("firstName"));
        var lastName = StringUtils.capitalize(ctx.formParam("lastName"));
        var email = ctx.formParam("email").trim().toLowerCase();
        var password = ctx.formParam("password");
        var encryptedPassword = Security.encrypt(password);
        var token = Security.generateToken();

        var user = new User(firstName, lastName, email, encryptedPassword, token);
        UserRepository.save(user);

        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) throws Exception {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var token = ctx.cookie("token") == null ? null : ctx.cookie("token");
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        if (user == null || !user.getToken().equals(token)) {
            ctx.redirect(NamedRoutes.buildUserPath());
            return;
        }
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }
}

//Реализуйте два обработчика:
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
