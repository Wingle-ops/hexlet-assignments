package exercise;

public class LabelTag implements TagInterface {

    private String tag;
    private TagInterface childTag;

    LabelTag(String tag, TagInterface childTag) {
        this.tag = tag;
        this.childTag = childTag;
    }

    @Override
    public String render() {
        return "<label>" + tag + "" + childTag.render() + "</label>";
    }

}
