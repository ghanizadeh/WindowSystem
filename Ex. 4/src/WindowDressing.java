
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

//implements TitleBarDelegate for handle actions from title bar
public class WindowDressing extends GraphicObject implements TitleBarDelegate
{
    public SimpleWindow window;
    //this list is for any other dressers for window
    LinkedList<GraphicObject> dressingList = new LinkedList<>();
    public TitleBar titleBar;
    //whole rectangle that window covers including title bar
    private Rectangle2D.Float windowRect;
    private WindowDelegate delegate;
    
    public void setWindowDelegate(WindowDelegate _delegate)
    {
        this.delegate = _delegate;
    }
    public WindowDressing(SimpleWindow w)
    {
        window = w;
        titleBar =new TitleBar(w);
        windowRect = new Rectangle2D.Float(titleBar.x,titleBar.y, w.width+0.01f, w.height + titleBar.getHeight()+0.01f);
        //adding delegate to titlebar
        titleBar.setTitleBarDelegate(this);
        x = titleBar.x;
        y = titleBar.y;
    }       
    
    public boolean isMouseOverWindow(float x, float y) 
    {
        //check if coordinates are inside of a window
        return windowRect.contains(x, y);
    }
    public Rectangle2D.Float getWindowRect()
    {
        return windowRect;
    }
    public boolean getMouseEvent(MouseEvent event)
    {
        return titleBar.getMouseEvent(event);
        
    }
    @Override
    public void shiftPosition(float _x, float _y) 
    {
        //move window
        windowRect.x = this.x += _x;
        windowRect.y = this.y += _y;
        titleBar.shiftPosition(_x, _y);
        window.shiftPosition(_x, _y);
        //shifting other dressings for window if needed. (i.e. shadow,etc)
        for(GraphicObject obj : this.dressingList)
        {
            obj.shiftPosition(_x, _y);
        }
    }

    @Override
    public void draw() 
    {
        titleBar.draw();
        //drawing other dressings for window if needed. (i.e. shadow,etc)
        for(GraphicObject obj : this.dressingList)
        {
            obj.draw();
        }

    }
    // overriten methods for title bar delegate 
    @Override
    public void closeWindow() 
    {
        if(this.delegate != null) {
            this.delegate.closeWindow(this);
        }
    }

    @Override
    public void minWindow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void maxWindow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void pressedTitleBar(float x, float y) 
    {
        if(this.delegate != null) {
            this.delegate.pressDragWindow(this, x, y);
        }
    }

    @Override
    public void releasedTitleBar(float x, float y) 
    {
        if(this.delegate != null) 
        {
            this.delegate.releaseDragWindow(null, x, y);
        }
    }



    
}
