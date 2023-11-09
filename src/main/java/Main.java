import domain_model.Controller;
import user_interface.UserInterface;

public class Main {
        public static void main(String[] args) {
            Controller controller = new Controller();
            UserInterface ui = new UserInterface(controller);
            ui.startProgram();
        }
    }
