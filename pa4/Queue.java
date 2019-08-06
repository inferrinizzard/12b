public class Queue implements QueueInterface {

	public Node head, tail;
	public int maxWait, totalWait, duration;

	class Node {
		Node next;
		Object job;

		public Node(Object j) {
			job = j;
			next = null;
		}
	}

	public Queue() {
		head = tail = null;
		maxWait = totalWait = duration = 0;
	}

	public boolean isEmpty() {
		return length() == 0;
	};

	public int length() {
		int sum = 0;
		Node ptr = head;
		while (ptr != null) {
			ptr = ptr.next;
			sum++;
		}
		return sum;
	}

	public void enqueue(Object newItem) {
		// Job newJob = newItem instanceof Job ? (Job) newItem : new Job(0, 0);
		// newJob.computeFinishTime(newJob.getArrival());
		// totalWait += newJob.getWaitTime();
		// maxWait = newJob.getWaitTime() > maxWait ? newJob.getWaitTime() : maxWait;
		Node push = new Node(newItem);
		if(push.job instanceof Job) duration += ((Job)push.job).getDuration();		//edit
		if (head == null)
			head = tail = push;
		else {
			tail.next = push;
			tail = push;
		}
	}

	public Object dequeue() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("dequeue");
		Node temp = head;
		head = head.next;
		if(temp.job instanceof Job) duration += ((Job)temp.job).getDuration();		//edit
		return temp.job;
	}

	public void dequeueAll() throws QueueEmptyException {
		while (length() > 0) {
			try {
				dequeue();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	public Object peek() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("peek");
		return head.job;
	}

	public String toString() {
		Node ptr = head;
		String result = "";
		while (ptr != null) {
			result += ptr.job.toString() + " ";
			ptr = ptr.next;
		}
		return result;
	}
}