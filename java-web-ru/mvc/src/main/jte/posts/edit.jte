@import exercise.dto.posts.BuildPostPage
@import BuildPostPage page

@template.layout.page(content = @'
@if(page.getErrors() != null)
    <div>
        <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
        </ul>
    </div>
@endif

<form action="pages" method="post">
    <div>
        <label>
            Название поста
            <input type="text" required name="name" value="${page.getName()}"/>
        </label>
    </div>

    <div>
        <label>
            Содержимое поста
            <input type="text" required name="body" value="${page.getBody()}/>
        </label>
    </div>
    <input type="submit" name="submit" value="редактировать пост"/>
</form>
')