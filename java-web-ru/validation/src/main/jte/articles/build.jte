@template.layout.page(content = @'
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
