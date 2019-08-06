public class QueueTest {
	public static void main(String[] args) {
		Queue q = new Queue();
		q.enqueue(new Job(1, 1)); // test enqueue
		System.out.println(q); // test toString
		try {
			q.dequeue(); // test dequeue
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println(q);
		q.enqueue(new Job(1, 2));
		q.enqueue(new Job(3, 4));
		q.enqueue(new Job(5, 6));
		try {
			if (q.peek() instanceof Job)
				((Job) q.peek()).computeFinishTime(((Job) q.peek()).getArrival()); // test peek
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println(q);
		System.out.println(q.length()); // test length
		try {
			q.dequeueAll(); // test dequeueAll
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println(q.isEmpty()); // test empty

	}
}