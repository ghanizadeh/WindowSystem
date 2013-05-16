import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class WindowManager implements WindowDelegate {
    
    private static WindowManager instance;
    private LinkedList<WindowDressing> windowDressingList = new LinkedList<>();
    private WindowSystem windowSystem = WindowSystem.getInstence();
    private MouseEvent prevMouseEvent;
    private WindowDressing focusedWindow;
    private boolean isDragMode = false;
    public static WindowManager getInstence()
    {
        if(instance == null)
        {
            instance = new WindowManager();
        }
        return instance;
    }
    
    public WindowDressing dressWindow(SimpleWindow w)
    {
        //"dress" window
        WindowDressing dw = new WindowDressing(w);
        dw.setWindowDelegate(instance);
        windowDressingList.addLast(dw);
        return dw;
    }
    
    public void bringWindowToFront(WindowDressing wd)
    {
        /**reorder windows, to make wd the most front window*/
        windowDressingList.remove(wd);
        windowDressingList.addLast(wd);
        windowSystem.bringGraphicObjectToFront(wd);
        windowSystem.bringSimpleWindowToFront(wd.window);

        //request redraw desktop area
        windowSystem.repaintRect(wd.getWindowRect());
        focusedWindow = wd;
    }
    
    /**For all events we check,
     * if we have focused window.
     * Send events to window which is in focus
     */
    public void mouseClicked(float x, float y)
    {
        MouseEvent event = new MouseEvent(MouseEventType.CLICKED,x,y);
        if(focusedWindow !=null) {
            focusedWindow.getMouseEvent(event);
        }
    }

    //get fron most window, at positioin x,y
    private WindowDressing getWindowAt(float x, float y)
    {
        /**iterate from a last element in the list
         because, last element is a most front element on the screen*/
        for(int i = windowDressingList.size()-1; i>=0; --i)
        {   
            WindowDressing wd = windowDressingList.get(i);
            if(wd.isMouseOverWindow(x, y))
            {
                return wd;
            }
        }
        return null;
    }
    
    public void mouseMoved(float x, float y)
    {
        MouseEvent event = new MouseEvent(MouseEventType.MOVED,x,y);
        focusedWindow = getWindowAt(x,y);
        if(focusedWindow !=null) {
            focusedWindow.getMouseEvent(event);
        }
    }   
    
    public void mouseDragged(float x, float y)
    {
        MouseEvent event = new MouseEvent(MouseEventType.DRAGGED,x,y);
        if(focusedWindow !=null) {
            focusedWindow.getMouseEvent(event);
        }
        dragWindow(x, y);
   
    }
    
    public void dragWindow(float x, float y)
    {
        if(isDragMode)
        {
            
            /**keep old rectangle of WindowDressing,
             * to be able to redraw old desktop area
             */        
            Rectangle2D.Float oldRect = (Rectangle2D.Float) this.focusedWindow.getWindowRect().clone();
            
            /**Calculate shift of coordinates*/
            float shiftX = x - this.prevMouseEvent.getX();
            float shiftY = y - this.prevMouseEvent.getY();
            //shift position
  
            
//            System.out.println("Shift");
//            System.out.println(shiftX);
//            System.out.println(shiftY);
            float newX = this.focusedWindow.x + shiftX;
            float newY = this.focusedWindow.y + shiftY;


            //restrict window movement to desktop sizes 
            if(newX>0.97f ||x>0.97f) shiftX = 0;
            if(newX<-0.03f||x<-0.03f)shiftX = 0;
            if(newY>0.97f ||y>0.97f)shiftY = 0; 
            if(newY<0.03f ||y<0.03f)shiftY = 0;  
            this.focusedWindow.shiftPosition(shiftX, shiftY);

            //request repaint of new and old rectangles
            windowSystem.repaintRect(this.focusedWindow.getWindowRect());
            windowSystem.repaintRect(oldRect);

            /**Store previous mouse event 
             * Needed to calculate shift of mouse move */
            this.prevMouseEvent = new MouseEvent(MouseEventType.DRAGGED,x,y);;
        
        }
    }
    
    public void mousePressed(float x, float y)
    {
        MouseEvent event = new MouseEvent(MouseEventType.PRESSED,x,y);
        prevMouseEvent = event;
        focusedWindow = getWindowAt(x,y);
        if(focusedWindow!=null)
        {
            bringWindowToFront(focusedWindow);
            focusedWindow.getMouseEvent(event);
        }
    }
    
    public void mouseReleased(float x, float y)
    {
        MouseEvent event = new MouseEvent(MouseEventType.RELEASED,x,y);
        if(focusedWindow !=null) {
            focusedWindow.getMouseEvent(event);
        }
    }
    
    
    //delegated methods from window delegate, with window functions
    @Override
    public void closeWindow(WindowDressing wd)
    {
        //remove window from structure
        windowSystem.removeGraphicObject(wd);
        windowSystem.removeSimpleWindow(wd.window);
        windowDressingList.remove(wd);
        //request redraw desktop area
        windowSystem.repaintRect(wd.getWindowRect());
    }

    @Override
    public void minWindow(WindowDressing wd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void resizeWindow(WindowDressing wd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void maxWindow(WindowDressing wd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    //enter semi-mode for dragging
    @Override
    public void pressDragWindow(WindowDressing wd, float x, float y) 
    {
        focusedWindow = wd;
        isDragMode = true;
    }

    //exit semi-mode for dragging
    @Override
    public void releaseDragWindow(WindowDressing wd, float x, float y) 
    {
       isDragMode = false;
    }


}
