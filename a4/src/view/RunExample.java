package view;
import controller.Controller;
import model.MyException;

public class RunExample extends Command {
    private Controller controller;
    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }
    @Override
    public void execute() {
        try {
            controller.allStep();
            System.out.println(controller.getOut(controller.getRepository().getCrtPrg()));
        } catch (MyException error) {
            System.out.println(error.getMessage());
        }
    }
}
