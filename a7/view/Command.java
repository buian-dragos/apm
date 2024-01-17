package view;

import controller.Controller;

public abstract class Command {
    private String key, description;
    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }
    public abstract void execute();
    public abstract Controller getController();
    public String getKey() {
        return key;
    }
    public String getDescription() {
        return description;
    }
}

