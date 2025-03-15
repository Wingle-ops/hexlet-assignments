@import exercise.controller.SessionController
@import SessionController page

@template.layout.page(content = @'

<form action="/sessions" method="post">
    @if(${page.getError()} != null)
        <div>
            <p>${page.getName()}, ${page.getError()}</p>
        <div>
    @endif
<input type="text" required name="name"/>
<input type="text" required name="password">
<input type="submit">
</form>
')










Реализуйте шаблон с формой логина. Для входа в систему мы должны запрашивать у пользователя его имя и пароль.
Имена полей формы используйте следующие: name для имени пользователя и password для пароля. Это важно для работы тестов