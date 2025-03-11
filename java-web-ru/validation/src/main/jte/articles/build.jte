@import exercise.dto.articles.BuildArticlesPage
@import BuildArticlesPage page

@template.layout.page(content = @'

@if(page.getErrors() != null)
    @for(var validator : page.getErrors().values())
        @for(var el : validator)
            ${el.getMessage()}
        @endfor
    @endfor
@endif

<form action="/articles" method="post">
    <div>
        <label>
            Название статьи
            <input type="text" name="title"/>
        </label>
    </div>
    <div>
        <label>
            Содержимое статьи
            <textarea name="content"></textarea>
        </label>
    </div>
    <input type="submit" name="Отправить"/>
</form>
')
