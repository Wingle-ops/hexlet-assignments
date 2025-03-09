package exercise;

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> {
        config.bundledPlugins.enableDevLogging();});

        app.get("/phones", ctx -> ctx.json(Data.getPhones()));
        app.get("/domains", ctx -> ctx.json(Data.getDomains()));
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}


//Реализуйте Javalin-приложение и добавьте в него:
//
//Обработчик GET /phones. Он должен возвращать список телефонов, закодированный в json
//Обработчик GET /domains. Он должен возвращать список доменов, закодированный в json
//
//Список телефонов и доменов можно получить, вызвав соответствующие статические методы класса Data: