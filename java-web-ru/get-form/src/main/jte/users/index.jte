@import exercise.App
@param UsersPage page

<form action="/users" method="get">
<input type="search" name="term" value="${page.getfirstName()}"/>
</form>

@if (!page.isEmpty()) {
    <ul>
        for (User user : page) {
            <li>${user.getfirstName()}</li>
        }
    <ul>
}
