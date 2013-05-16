import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class WindowSystem extends GraphicsEventSystem
{
    //size of desktop
    private int width;
    private int height;
    private LinkedList<GraphicObject> graphicObjects = new LinkedList<>();
    private LinkedList<SimpleWindow> simpleWindows = new LinkedList<>();

    /** making a window system a singleton,
    * so we have only one window system in application
    */
    private static WindowSystem instance;
    private static WindowManager windowManager = WindowManager.getInstence();
    
    public void addSimpleWindow(SimpleWindow w)
    {
        simpleWindows.addLast(w);
        graphicObjects.addLast(w);
        WindowDressing dw = windowManager.dressWindow(w);
        graphicObjects.addLast(dw);
    }
    public void removeSimpleWindow(SimpleWindow w)
    {
        simpleWindows.remove(w);
        graphicObjects.remove(w);
       
    }
    public void bringSimpleWindowToFront(SimpleWindow w)
    {
        //reorder window
        simpleWindows.remove(w);
        simpleWindows.addLast(w);
        bringGraphicObjectToFront(w);
    }
    public void bringGraphicObjectToFront(GraphicObject obj)
    {
        //reorde
        graphicObjects.remove(obj);
        graphicObjects.addLast(obj);
    }
    public void addGraphicObject(GraphicObject obj)
    {
        // adding window as graphical object for drawing
        graphicObjects.addLast(obj);
    }
    
    public void removeGraphicObject(GraphicObject obj)
    {
        //removing object 
        graphicObjects.remove(obj);
    }
    

    WindowSystem(int i, int j)
    {
        super(i,j);
        setBackground(Color.darkGray);
        width = i;
        height = j;

    }
    
    public static WindowSystem getInstence()
    {
        if(instance == null)
        {
            instance = new WindowSystem(800,600);
        }
        return instance;
    }
    
    // convert X abstract coordinate to desktop one 
    private int abstToDeskX(float x)
    {
        return (int)(width*x);
    }
    
    // convert Y abstract coordinate to desktop one 
    private int abstToDeskY(float y)
    {
        return (int)(height*y);
    }
        
    // convert X abstract coordinate to desktop one 
    private float deskXToAbst(int x)
    {
        return ((float)x/width);
    }
    
    // convert Y abstract coordinate to desktop one 
    private float deskYToAbst(int y)
    {
        return ((float)y/height);
    }
    
    public void repaintRect(Rectangle2D.Float r)
    {
        int x = abstToDeskX(r.x);
        int y = abstToDeskY(r.y);
        int width = abstToDeskX(r.width);
        int height = abstToDeskY(r.height);
        requestRepaint(new Rectangle(x,y,width,height));
    }
    
    @Override
    public void handlePaint()
    {
        //System.out.println("Handle Paint");
        // interate over each graphicObject and draw it 
        for(GraphicObject obj : this.graphicObjects)
        {
            obj.draw();
        }
     
    }
    
    public void drawLine(float StartX, float StartY, float EndX, float EndY)
    {
        this.drawLine(abstToDeskX(StartX), abstToDeskY(StartY), abstToDeskX(EndX),abstToDeskY(EndY));
    }
    public void drawRect(float inX, float inY, float inWidth, float inHeight)
    {
        this.drawRect(abstToDeskX(inX),abstToDeskY(inY),abstToDeskX(inWidth),abstToDeskY(inHeight));
    }
    public void fillRect(float inX, float inY, float inWidth, float inHeight)
    { 
         this.fillRect(abstToDeskX(inX),abstToDeskY(inY),abstToDeskX(inWidth),abstToDeskY(inHeight));
    }
    public void drawString(String text, float x, float y)
    {
        this.drawString(text,abstToDeskX(x), abstToDeskY(y));
    }
    
    @Override
    public void handleMouseClicked(int x, int y)
    {    

        windowManager.mouseClicked(deskXToAbst(x), deskYToAbst(y));
    }
    @Override
    public void handleMouseDragged(int x, int y)
    {   
        //check if move mouse outside of desktop
        windowManager.mouseDragged(deskXToAbst(x), deskYToAbst(y));
    }
    @Override
    public void handleMousePressed(int x, int y)
    {   
        windowManager.mousePressed(deskXToAbst(x), deskYToAbst(y));
    }
    @Override
    public void handleMouseReleased(int x, int y)
    {   
        windowManager.mouseReleased(deskXToAbst(x), deskYToAbst(y));
    }
    @Override
    public void handleMouseMoved(int x, int y)
    {   
        windowManager.mouseMoved(deskXToAbst(x), deskYToAbst(y));
    }
}