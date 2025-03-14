@import exercise.dto.posts.EditPostPage
@import EditPostPage page

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

<form action="pages/${page.getId()}" method="post">
    <div>
        <label>
            Название поста
            <input type="text" required name="name" value="${page.getName()}"/>
        </label>
    </div>

    <div>
        <label>
            Содержимое поста
            <textarea type="text" name="body">${page.getBody()}</textarea>
        </label>
    </div>
    <input type="submit" name="submit"/>
</form>
')