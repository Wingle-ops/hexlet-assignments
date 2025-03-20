package exercise.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Получаем имя из параметров запроса
        String name = req.getParameter("name");

        // Проверяем, если имя не было передано или пустое
        if (name == null || name.isEmpty()) {
            name = "Guest"; // Устанавливаем значение по умолчанию
        }

        // Формируем приветственное сообщение
        String welcome = "Hello, " + name + "!";

        // Устанавливаем атрибут для передачи в JSP
        req.setAttribute("welcome", welcome);

        // Перенаправляем на JSP-страницу
        req.getRequestDispatcher("WEB-INF/hello.jsp").forward(req, res);
    }
}
