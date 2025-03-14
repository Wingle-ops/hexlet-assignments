package exercise.dto.posts;

import java.util.List;
import java.util.Map;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditPostPage {
    Long id;
    String name;
    String body;
    private Map<String, List<ValidationError<Object>>> errors;
//
//    public EditPostPage(Long id, String name, String body, Map<String, List<ValidationError<Object>>> errors) {
//        this.id = id;
//        this.name = name;
//        this.body = body;
//    }
}
