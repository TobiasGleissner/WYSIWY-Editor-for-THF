package gui;

public enum HighlightingStyle {
    NORMAL              ("Normal Text",         "body",             null,      null),
    FORMULA_ROLE        ("Formula Role",        "formula_role",     "#CA6000", null),
    VARIABLE            ("Variable",            "variable",         "#04859D", null),
    CONSTANT            ("Constant",            "constant",         "#008500", null),
    DEFINED_CONSTANT    ("Defined Constant",    "defined_constant", "#008500", null),
    SYSTEM_CONSTANT     ("System Constant",     "system_constant",  "#008500", null),
    TYPE                ("Type",                "type",             "#DD0000", null),
    DEFINED_TYPE        ("Defined Type",        "defined_type",     "#DD0000", null),
    SYSTEM_TYPE         ("System Type",         "system_constant",  "#DD0000", null),
    COMMENT             ("Comment",             "comment",          "#777777", null);

    private String desc;
    private String cssName;
    private String defaultFG;
    private String defaultBG;

    private HighlightingStyle(String desc, String cssName, String defaultFG, String defaultBG) {
        this.desc = desc;
        this.cssName = cssName;
        this.defaultFG = defaultFG;
        this.defaultBG = defaultBG;
    }

    public String toString() {
        return this.desc;
    }

    public String getCssName() {
        return this.cssName;
    }

    public String getDefaultFG() {
        return this.defaultFG;
    }

    public String getDefaultBG() {
        return this.defaultBG;
    }

    public void setColor(boolean bg, String cssColor) {
        Config.setColor(this.cssName, bg, cssColor);
    }

    public String getColor(boolean bg) {
        return Config.getColorInternal(this.cssName, bg, bg ? this.getDefaultBG() : this.getDefaultFG());
    }
};
