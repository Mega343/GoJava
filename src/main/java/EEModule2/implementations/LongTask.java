package EEModule2.implementations;

import EEModule2.interfaces.Task;

public class LongTask implements Task<Long> {

    private Long result;

    public LongTask(Long result) {
        this.result = result;
    }

    @Override
    public void execute() {

    }

    @Override
    public Long getResult() {
        return result;
    }
}
