package diningPhilosoph;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author malik
 */
/*
To make code more readable the DiningPhilosophers.java got a bit of a makeover, makes it more readable
*/
public class ChopStick 
{
    //Make sure only one philosph can have it a time
    Lock up = new ReentrantLock();
    
    //Who am I
    private final int id;

    public ChopStick(int id) {
        this.id = id;
    }
    
    public boolean pickUp(Philosopher who, String where) throws InterruptedException
    {
        if(up.tryLock(10, TimeUnit.MILLISECONDS))
        {
            System.out.println(who + " picked up " + where + " " + this);
            return true;
        }
        return false;
    }
    
    public void putDown(Philosopher who, String name)
    {
        up.unlock();
        System.out.println(who + " put down " + name + " " + this);
    }

    @Override
    public String toString() {
        return "ChopStick-" + id;
    }
    
    
}
