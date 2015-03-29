/**
 * Class ShutdownHook - Class to call the flush() method in ContactManagertImpl, whenever the program exits.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public class ShutdownHook 
{
  public void shutdown()
  {
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      @Override
      public void run()
      {
        ContactManagerImpl manager = new ContactManagerImpl();
        manager.flush();
      }      
    });
  }
}