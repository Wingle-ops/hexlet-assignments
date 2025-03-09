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
            for (Map<String, String> map : COMPANIES) {
                if (map.get("id").equals(id)) {
                    ctx.json(map);
                    break;
                }
            }
            ctx.status(404).result("Company not found");
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
