package api;

public class Link {
    private String rel;
    private String href;
    private String action;

    public Link() {
    }

    public Link(String rel, String href, String action) {
        this.rel = rel;
        this.href = href;
        this.action = action;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
