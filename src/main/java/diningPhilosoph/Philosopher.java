package diningPhilosoph;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author malik
 */
/**
 * A philosopher alternates between thinking and eating. To eat, the philosopher
 * needs to pick up the left chopstick and then the right chopstick
 * sequentially. The phillosopher shares chopsticks with its neighbors, so it
 * cannot eat at the same time as either neighbor.
 *
 * @author Barbara Lerner
 * @version Oct 5, 2010
 *
 */
public class Philosopher implements Runnable {

    // Which one I am.
    private final int id;
    // The chopsticks on either side of me.
    private final ChopStick leftChopStick;
    private final ChopStick rightChopStick;
    // Am I full?
    volatile boolean isTummyFull = false;
    // To randomize eat/Think time
    private Random randomGenerator = new Random();
    // Number of times I was able to eat.
    private int noOfTurnsToEat = 0;

    /**
     * Constructs a new philosopher
     *
     * @param id the unique id
     * @param leftChopstick chopstick to the left
     * @param rightChopstick chopstick to the right
     */
    public Philosopher(int id, ChopStick leftChopStick, ChopStick rightChopStick) {
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
    }

    /**
     * Repeatedly think, pick up chopsticks, eat and put down chopsticks
     */
    @Override
    public void run() {

        try 
        {
            while (!isTummyFull) 
            {
                // Think for a bit.
                think();
                // Make the mechanism obvious.
                if (leftChopStick.pickUp(this, "left")) 
                {
                    if (rightChopStick.pickUp(this, "right")) 
                    {
                        // Eat some.
                        eat();
                        // Finished.
                        rightChopStick.putDown(this, "right");
                    }
                    // Finished.
                    leftChopStick.putDown(this, "left");
                }
            }
        } catch (Exception e) {
            // Catch the exception outside the loop.
            e.printStackTrace();
        }
    }

    /**
     * Lets a random amount of time pass to model thinking.
     *
     * @throws InterruptedException
     */
    private void think() throws InterruptedException {
        System.out.println(this + " is thinking");
        Thread.sleep(randomGenerator.nextInt(1000));
    }

    /**
     * Lets a random amount of time pass to model eating.
     *
     * @throws InterruptedException
     */
    private void eat() throws InterruptedException {
        System.out.println(this + " is eating");
        noOfTurnsToEat++;
        Thread.sleep(randomGenerator.nextInt(1000));
    }

    // Accessors at the end.
    public int getNoOfTurnsToEat() {
        return noOfTurnsToEat;
    }

    @Override
    public String toString() {
        return "Philosopher-" + id;
    }
}
