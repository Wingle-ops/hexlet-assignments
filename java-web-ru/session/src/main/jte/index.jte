@import exercise.dto.MainPage
@import exercise.util.NamedRouters
@import exercise.model.User
@import MainPage cookie
@import User user

@template.layout.page(coontent = @'
<!DOCTYPE html>
    <html lang="ru">
        <body>
            @if(${cookie.getName()} == null) {
                <a href=${NamedRouters.loginPath()}>Войти</a>
            } else {
                <div>
                    <p>${user.getName()}</p>
                    <form action="/sessions" method="post">
                        <button type="submit">Выйти</button>
                    </form>
                <div>
            }
        </body>
    </html>
')

    Если пользователь не аутентифицирован, то ему показывается ссылка на страницу логина с текстом Войти
    Если аутентифицирован, то его имя и форма с кнопкой Выйти
