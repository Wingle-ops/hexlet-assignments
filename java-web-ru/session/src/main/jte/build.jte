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
