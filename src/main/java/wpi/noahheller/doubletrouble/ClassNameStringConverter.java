package wpi.noahheller.doubletrouble;

import javafx.util.StringConverter;

public class ClassNameStringConverter<T> extends StringConverter<T> {
    @Override
    public String toString(T object) {
        return object.getClass().getSimpleName();
    }

    @Override
    public T fromString(String string) {
        return null;
    }
}
