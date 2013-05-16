public class MyApp {
   
    public static void main(String[] args)
    {
    	WindowSystem windowSystem = WindowSystem.getInstence();
        SimpleWindow w = new SimpleWindow(0.1f,0.1f,0.2f,0.2f);
        w.title = "Window 1";
        windowSystem.addSimpleWindow(w);
       
        SimpleWindow w1 = new SimpleWindow(0.5f,0.5f,0.4f,0.4f);
        w1.title = "Window 2";
        windowSystem.addSimpleWindow(w1);
        
        SimpleWindow w2 = new SimpleWindow(0.2f,0.2f,0.2f,0.2f);
        w2.title = "Window 3";
        windowSystem.addSimpleWindow(w2);
        
        
    }
}
