package EEModule2.implementations;

import EEModule2.interfaces.Validator;

public class NumberValidator implements Validator<Number> {

    @Override
    public boolean isValid(Number result) {
        return (result.intValue() > 0);
    }
}
