package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/companies/{id}", ctx -> {
            String id = ctx.pathParam("id");
            boolean found = false; // Флаг для отслеживания найденной компании

            for (Map<String, String> map : COMPANIES) {
                if (map.get("id").equals(id)) {
                    ctx.json(map);
                    found = true; // Устанавливаем флаг в true, если компания найдена
                    break; // Выходим из цикла
                }
            }

            if (!found) {
                ctx.status(404).json(Map.of("error", "Company not found", "code", 404)); 
            }
        });

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
