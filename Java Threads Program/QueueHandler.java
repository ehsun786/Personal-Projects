import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class QueueHandler implements Runnable {

	ThreadPoolExecutor e;
	MemManager m;
	String filename;

	public QueueHandler(ThreadPoolExecutor e, MemManager m, String f) {
		this.e = e;
		this.m = m;
		this.filename = f;
	}

	@Override
	public void run() {
		//Read the file pdata.txt in this method.
		String fname = filename;
		Path fpath = Paths.get(fname);
		try (Scanner file = new Scanner(fpath)) {
			int delay, size, rt;
			char pid;

			while (file.hasNextLine()) {
				Scanner line = new Scanner(file.nextLine());
				line.useDelimiter(":");
				delay = line.nextInt();
				pid = line.next().charAt(0);
				size = line.nextInt();
				rt = line.nextInt();
				line.close();
				Thread.sleep(100*delay);
				Process p = new Process(m, pid, size, rt);
				e.execute(p);
			}
			//Close reading the file and shutdown the Thread pool to shutdown the program after everything has been read.
			e.shutdown();
			file.close();
		} catch (NoSuchFileException e) {
			System.err.println("File not found: " + fname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
