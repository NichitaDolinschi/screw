package md.dolinschi.screw.configurator;

import md.dolinschi.screw.reflection.ObjectField;

import java.util.Map;
import java.util.function.UnaryOperator;

public class DefaultConfigurator implements Configurator {
    private Map<String, UnaryOperator<Object>> beforeSet;

    @Override
    public void configure(ObjectField objectField) {

    }
}
