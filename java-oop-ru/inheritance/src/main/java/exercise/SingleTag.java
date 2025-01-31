package exercise;

import java.util.Map;

public class SingleTag extends Tag {
    private String name;
    private Map<String, String> attributes;

    public SingleTag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("<").append(name);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            res.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        res.append(">"); // Закрывающий тег
        return res.toString();
    }
}