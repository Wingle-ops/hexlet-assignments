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

    public static void reg(Context ctx) {
        String firstname = ctx.pathParam("firstName");
        String lastName = ctx.pathParam("lastName");
        String email = ctx.pathParam("email");
        String password = Security.encrypt(ctx.pathParam("password"));
        String token = Security.generateToken();

        User user = new User(firstname, lastName, email, password, token);
        UserRepository.save(user);
        ctx.cookie("visited", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) {
        String cookie = ctx.cookie("visited");
        var user = UserRepository.find(ctx.pathParamAsClass("{id}", Long.class).get());
        if ((user.get().getToken()).equals(cookie)) {
            ctx.render(NamedRoutes.usersPath() +  "/show.jte", model("page", user.get()));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
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
