package exercise.dto.articles;

import io.javalin.validation.ValidationError;
import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuildArticlePage {
    private String name;
    private String data;
    private Map<String, List<ValidationError<Object>>> errors;
}
