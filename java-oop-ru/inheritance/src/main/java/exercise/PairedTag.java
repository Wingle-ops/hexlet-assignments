package exercise;

import java.util.List;
import java.util.Map;

public class PairedTag extends Tag {
    private String tagName;
    private Map<String, String> attributes;
    private String body;
    private List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<").append(tagName);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            result.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        result.append(">"); // Закрытие открывающего тега

        result.append(body); // Добавление тела тега

        for (Tag child : children) {
            result.append(child.toString()); // Добавление каждого дочернего тега
        }

        result.append("</").append(tagName).append(">"); // Закрывающий тег
        return result.toString();
    }
}