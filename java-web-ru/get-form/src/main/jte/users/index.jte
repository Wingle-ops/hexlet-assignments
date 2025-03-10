@import exercise.App
@param UsersPage page

<form action="/users" method="get">
    <input type="search" name="term" value="${page.getFirstName()}" placeholder="Search users..." />
    <button type="submit">Search</button>
</form>

@if (!page.getUsers().isEmpty()) {
    <ul>
        @for (User  user : page.getUsers()) {
            <li>${user.getFirstName()}</li>
        }
    @endfor
    </ul>
}
