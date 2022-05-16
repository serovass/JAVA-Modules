package renderer;

import preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {

    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderText(String message) {

        System.out.println(preProcessor.preProcess(message));
    }
}
