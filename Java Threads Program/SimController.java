import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimController implements Runnable {

	static ThreadPoolExecutor e = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	static MemManager memManagerObject;
	static Thread threadSimController;
	static Thread threadQueueHandler;
	public SimController() {

	}

	@Override
	public void run() {
		while(!e.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(memManagerObject.isChanged()) {
				System.out.println(memManagerObject.toString());
			}
		}
		memManagerObject.toString();
	}

	public static void main(String[] args) {
		
		String type = args[0];
		String size = args[1];
		String filename = args[2];
		
		if(type.equals("b")) {
			System.out.println("Policy: " + "Best Fit");
			memManagerObject = new BestFitMemManager(Integer.parseInt(size));
			threadSimController = (new Thread(new SimController()));
			threadSimController.start();
			threadQueueHandler = new Thread(new QueueHandler(e, memManagerObject, filename));
			threadQueueHandler.start();
		} else if(type.equals("w")) {
			System.out.println("Policy: " + "Worst Fit");
			memManagerObject = new WorstFitMemManager(Integer.parseInt(size));
			threadSimController = (new Thread(new SimController()));
			threadSimController.start();
			threadQueueHandler = new Thread(new QueueHandler(e, memManagerObject, filename));
			threadQueueHandler.start();
		} else if(type.equals("f")) {
			System.out.println("Policy: " + "First Fit");
			memManagerObject = new FirstFitMemManager(Integer.parseInt(size));
			threadSimController = (new Thread(new SimController()));
			threadSimController.start();
			threadQueueHandler = new Thread(new QueueHandler(e, memManagerObject, filename));
			threadQueueHandler.start();
		}
		try {
			threadSimController.join();
			threadQueueHandler.join();
			System.out.println("All threads have terminated");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
