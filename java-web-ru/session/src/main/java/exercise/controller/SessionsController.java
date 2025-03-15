package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
//import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
//import io.javalin.http.NoContentResponse;

public class SessionsController {

    static MainPage coockie = new MainPage(null);

    public static void build(Context ctx) {
        ctx.render(NamedRoutes.buildSessionPath());
    }

    public static void login(Context ctx) {
        String name = ctx.formParamAsClass("name", String.class).get();
        String password = Security.encrypt(ctx.formParamAsClass("password", String.class).get());
        var user = UsersRepository.findByName(name);
        if ((user.get().getPassword().equals(password))
                && (user.get().getName()).equals(name)) {
            ctx.sessionAttribute("session", name);
            coockie = ctx.sessionAttribute("session");
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            LoginPage page = new LoginPage("error", "Wrong username or password");
            ctx.render(NamedRoutes.loginPath(), model("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("session", null);
        coockie = ctx.sessionAttribute("session");
        ctx.redirect(NamedRoutes.rootPath());
    }
}


//GET /sessions/build — форма логина, в которой пользователь вводит имя и пароль
//POST /sessions для создания сессии
//POST /sessions/delete для удаления сессии
//
//После выполнения каждого из этих действий происходит редирект на главную страницу.
//
//Реализуйте обработчики для указанных выше маршрутов.
//Для работы с пользователями используйте репозиторий UsersRepository.
//
//Список пользователей с именами и паролями можно посмотреть в классе Generator.
//        Обратите внимание, что пароль хранится в зашифрованном виде.
//        Это значит, что при сравнении необходимо шифровать пароль от пользователя и сравнивать хеши.
//
//Если имя или пароль неверные, то снова отрисовывается форма
// логина и показывается сообщение Wrong username or password.