import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphores {
   Semaphores() {
       List<Integer> arrayList = new LinkedList<>();
       int capacity = 50;
       Semaphore semaphoreProducer = new Semaphore(1);
       Semaphore semaphoreConsumer = new Semaphore(0);

       Producer producer = new Producer(arrayList, capacity, semaphoreProducer, semaphoreConsumer);
       Consumer consumer = new Consumer(arrayList, capacity, semaphoreProducer, semaphoreConsumer);

       Thread t1 = new Thread(producer);
       Thread t2 = new Thread(consumer);

       ExecutorService es = Executors.newFixedThreadPool(2);
       es.submit(t1);
       es.submit(t2);
       es.shutdown();
   }
}

class Producer implements Runnable {
    List<Integer> linkedList;
    Semaphore semaphoreProducer;
    Semaphore semaphoreConsumer;
    int capacity;

    public Producer(List<Integer> linkedList, int capacity, Semaphore semaphoreProducer, Semaphore semaphoreConsumer) {
        this.linkedList = linkedList;
        this.capacity = capacity;
        this.semaphoreProducer = semaphoreProducer;
        this.semaphoreConsumer = semaphoreConsumer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                semaphoreProducer.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            linkedList.add(i);
            System.out.println("Added " + i);
            semaphoreConsumer.release();
        }
    }
}

class Consumer implements Runnable {
    List<Integer> linkedList;
    Semaphore semaphoreProducer;
    Semaphore semaphoreConsumer;

    public Consumer(List<Integer> linkedList, int capacity, Semaphore semaphoreProducer, Semaphore semaphoreConsumer) {
        this.linkedList = linkedList;
        this.semaphoreProducer = semaphoreProducer;
        this.semaphoreConsumer = semaphoreConsumer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphoreConsumer.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Removed: " + linkedList.remove(0));
            semaphoreProducer.release();
        }
    }
}
