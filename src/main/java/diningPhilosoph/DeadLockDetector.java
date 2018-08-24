package diningPhilosoph;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 *
 * @author malik
 */
public class DeadLockDetector extends Thread
{
    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    private boolean shouldRun = true;
    

    public void end()
    {
        shouldRun = false;
    }
    
    @Override
    public void run()
    {
        while(shouldRun)
        {
            //Finds cycles of threads that are in deadlock waiting to acquire object monitors or ownable synchronizers. T
            long[] threadIds = bean.findDeadlockedThreads(); 
            if(threadIds != null)
                System.out.println("Deadlock");
            //If a deadlock is detected the program must report to the console and shut down
            System.exit(1);
        }
    }
}
