import java.io.*;
import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HangFile implements Serializable, Runnable {
	
	private static final long serialVersionUID = -1892561327013038124L;

	static ArrayList<String> list0 = new ArrayList(50);
	static ArrayList<String> list1 = new ArrayList(50);
	static ArrayList<String> list2 = new ArrayList(50);
	static ArrayList<String> list3 = new ArrayList(50);

	static List[] array = { list0, list1, list2, list3 };

	public HangFile(String name, int arrayIndex) {
		try {
			WrongInput.checkString(name);
		} catch (WrongInput w) {
			w.getMessage();
		}
		Path path = Paths.get(name);
		Charset charset = Charset.forName("ISO-8859-1");
		try {
			List<String> lines = Files.readAllLines(path, charset);
			Collections.shuffle(lines);
			array[arrayIndex].addAll(lines.subList(0, 49));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void writeArrayContentsToFile() {
		try (FileOutputStream fo = new FileOutputStream("myBinFile.txt"); ObjectOutputStream obj = new ObjectOutputStream(fo);) {
			obj.writeObject(array);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("The contents of the array have been serialized (written in object form to the file myBin.txt");
	}

	public static void readArrayContentsFromFile() {
		try (FileInputStream fi = new FileInputStream("myBinFile.txt"); ObjectInputStream obj = new ObjectInputStream(fi);) {
			List[] newArray;
			newArray = (List[]) obj.readObject();
			for (List list : newArray) {
				System.out.println(Arrays.toString(list.toArray()));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException c) {
			System.out.println(c.getMessage());
		}
	}
	
	public static void runSequential() {
		new HangFile("file1.txt", 0);
		new HangFile("file2.txt", 1);
		new HangFile("file3.txt", 2);
		new HangFile("file4.txt", 3);
	}
	
	public static void runParallel() {
		 ExecutorService executorService = Executors.newFixedThreadPool(4);
		 executorService.submit(new HangFile("file1.txt", 0));
		 executorService.submit(new HangFile("file2.txt", 1));
		 executorService.submit(new HangFile("file3.txt", 2));
		 executorService.submit(new HangFile("file4.txt", 3));
		 executorService.shutdown();
		 try {
			executorService.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	@Override
	public void run() {
	
	}
}
