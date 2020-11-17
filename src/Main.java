import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new ReadFile();
        timer.schedule(task,0,5*1000);
    }
}
