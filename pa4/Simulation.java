import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Simulation {
	public static void main(String[] args) throws IOException {
		int numJobs = 0, maxWait = 0, totalWait = 0;
		float avgWait = 0;
		ArrayList<int[]> input = new ArrayList<int[]>();
		File file = new File(args[0]);
		PrintWriter trace = new PrintWriter(new FileWriter(args[0] + ".trc")),
				report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
		try {
			Scanner sc = new Scanner(file);
			numJobs = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < numJobs; i++) {
				String[] line = sc.nextLine().split(" ");
				input.add(new int[] { Integer.parseInt(line[0]), Integer.parseInt(line[1]) });
			}
			sc.close();

			report.println("Report file: " + args[0] + ".rpt\n" + numJobs + " Jobs:");
			trace.println("Trace file: " + args[0] + ".trc\n" + numJobs + " Jobs:");
		} catch (Exception e) {
			trace.println(e);
			System.exit(1);
		}

		for (int i = 1; i < numJobs; i++) {
			int time = 0, jobsLeft = numJobs;
			Queue[] processors = new Queue[i];
			for (int j = 0; j < i; j++)
				processors[j] = new Queue();
			Queue store = new Queue(), least = processors[0];
			input.forEach((s) -> store.enqueue(new Job(s[0], s[1])));

			report.println(i == 1 ? store + "\n\n***********************************************************" : "");
			report.print(i + " processor" + (i > 1 ? "s: " : ": "));
			trace.println(i == 1 ? store : "");
			trace.println((i == 1 ? "\n" : "")+ "*****************************\n" + i + " processor" + (i > 1 ? "s" : "") + ":\n*****************************"); 		//edit

			while (jobsLeft > 0) {
				boolean change = false;

				try {
					for (Queue q : processors) {
						if (!q.isEmpty() && !(q.peek() instanceof Job))
							return;
						if (!q.isEmpty() && time >= ((Job) q.peek()).getFinish()) {
							store.enqueue(q.dequeue());
							if (!q.isEmpty()) {
								Job head = (Job) q.peek();
								head.computeFinishTime(time);
								q.totalWait += head.getWaitTime();
								q.maxWait = head.getWaitTime() > q.maxWait ? head.getWaitTime() : q.maxWait;
							}
							change = true;
							jobsLeft--;
						}
					}

					while (!store.isEmpty() && time == ((Job) store.peek()).getArrival()) { // check multiple
						least = Stream.of(processors).reduce((min, q) -> q.length() < min.length() ? q : min).get();		//edit
						Job newJob = ((Job) store.dequeue());
						least.enqueue(newJob);
						if (least.length() == 1)
							newJob.computeFinishTime(time);
						change = true;
					}
				} catch (QueueEmptyException e) {
					trace.println(e);
					System.exit(2);		//edit
				}

				if (change || time == 0) {
					trace.println((time > 0 ? "\n" : "") + "time=" + (time));
					trace.println("0: " + store);
					for (int k = 0; k < processors.length; k++)
						trace.println(k + 1 + ": " + processors[k]);
					// Stream.of(processors).forEach(q -> trace.println(q));
				}
				time++;

			}
			maxWait = Stream.of(processors).mapToInt(q -> q.maxWait).max().getAsInt();
			totalWait = Stream.of(processors).mapToInt(q -> q.totalWait).sum();

			avgWait = totalWait / (float) numJobs;
			report.printf("totalWait=%d, maxWait=%d, averageWait=%.2f%s", totalWait, maxWait, avgWait, i == numJobs - 1 ? "\n" : "");
			time = 0;
		}
		report.close();
		trace.close();
	}

}