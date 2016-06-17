package EEModule2.implementations;


import EEModule2.interfaces.Executor;
import EEModule2.interfaces.Task;
import EEModule2.interfaces.Validator;

import java.util.ArrayList;
import java.util.List;

public class NumberExecutor implements Executor<Number> {

    List<Task<? extends Number>> tasks = new ArrayList<>();

    List<Number> validResults;
    List<Number> invalidResults;
    Validator validator;

    private boolean isExecuted;

    @Override
    public void addTask(Task<? extends Number> task) throws IllegalStateException {

        if (isExecuted){
            throw new IllegalStateException("Task list is already executed!");
        }

        tasks.add(task);
    }

    @Override
    public void addTask(Task<? extends Number> task, Validator<? super Number> validator) throws IllegalStateException {

        if (isExecuted){
            throw new IllegalStateException("Task list is already executed!");
        }

        tasks.add(task);
        this.validator = validator;
    }

    @Override
    public void execute() {
        validResults = new ArrayList<>();
        invalidResults = new ArrayList<>();

        for (Task<? extends Number> task: tasks) {
            task.execute();
            Number taskResult = task.getResult();

            if (validator.isValid(taskResult)){
                validResults.add(taskResult);
            } else {
                invalidResults.add(taskResult);
            }
        }

        isExecuted = true;
    }

    @Override
    public List<Number> getValidResults() {
        return validResults;
    }

    @Override
    public List<Number> getInvalidResults() {
        return invalidResults;
    }

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }
}