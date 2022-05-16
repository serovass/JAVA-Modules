package printer;

import renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private final Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "Default prefix";
    }

    public void setPrefix(String prefix){

        this.prefix = prefix;
    }

    @Override
    public void print(String message) {

        renderer.renderText(String.format("%s %s", this.prefix, message));
    }

}
