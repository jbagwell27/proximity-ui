package code.proximityui.data;

public class Card {

    private String name;
    private String editionCode;
    private Parameters parameters;


    public Card(String name) {
        this.name = name;
        parameters = new Parameters();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditionCode() {
        return editionCode;
    }

    public void setEditionCode(String editionCode) {
        this.editionCode = editionCode;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
