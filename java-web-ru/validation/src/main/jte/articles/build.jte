@import exercise.dto.articles.BuildArticlesPage
@import BuildArticlesPage page

@template.layout.page(content = @'

@if(page.getErrors() != null)
    @for(var validator : page.getErrors().values())
        @for(var el : validator)
            <p>${el.getMessage()}</p>
        @endfor
    @endfor
@endif

<form action="/articles" method="post">
    <div>
        <label>
            Название статьи
            <input type="text" name="title" value="${page.getTitle()}"/>
        </label>
    </div>
    <div>
        <label>
            Содержимое статьи
            <textarea name="content" value="${page.getContent()}"></textarea>
        </label>
    </div>
    <input type="submit" name="Отправить"/>
</form>
')
