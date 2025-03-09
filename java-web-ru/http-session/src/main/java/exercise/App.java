package exercise;
import io.javalin.Javalin;

import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        app.get("/users", ctx -> {
            int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            int per =ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
            var offset = (page - 1) * per;
            List<Map<String, String>> sliceOfUsers = USERS.subList(offset, offset + per);
            ctx.json(sliceOfUsers);
        });
        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

//        Реализуйте обработчик, который должен:
//
//Обрабатывать GET-запрос на адрес /users
//Отдавать по этому адресу список пользователей в виде json
//
//        Сделайте так, чтобы список пользователей отдавал только те записи, которые соответствуют текущей запрошенной странице.
//        По умолчанию должна выдаваться первая страница и пять результатов на запрос.
//
//Данные для пейджинга передаются в параметрах строки запроса. Примеры таких запросов:
//
//        /users – параметры строки запроса не переданы. Должна выдаваться первая страница с количеством результатов 5, то есть первые пять записей.
//    /users?page=5&per=3. Должна выдаваться пятая страница с количеством результатов по 3 на странице. То есть должны возвращаться 13, 14 и 15 запись из списка
