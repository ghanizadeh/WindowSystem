
import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class TitleBar extends GraphicObject implements ButtonActionDelegate {

 
    private float height = 0.05f;
    private float width;
    private Button closeBtn;
    private TitleBarDelegate delegate;
    private DrawString title;
    
    //setting delegate
    public void setTitleBarDelegate(TitleBarDelegate _delegate)
    {
        this.delegate = _delegate;
    }
    
    public TitleBar(SimpleWindow w)
    {
        y = w.y - height+0.00001f;
        x = w.x;
        width = w.width;
        closeBtn = new Button(ButtonType.CLOSE,w.x+0.01f,w.y - height+0.01f, 0.025f, 0.03f);
        title = new DrawString(w.title, x + 0.04f, y + 0.035f );
        closeBtn.setButtonActionDelegate(this);
    }
    @Override
    public void shiftPosition(float _x, float _y) 
    {
        closeBtn.shiftPosition(_x, _y);
        title.shiftPosition(_x, _y);
        
        this.x += _x;
        this.y += _y;
 
    }
    @Override
    public void draw() 
    {
        this.windowSystem.setColor(Color.green);
        this.windowSystem.fillRect(this.x, this.y , width, height);
        this.windowSystem.setColor(Color.black);
        this.windowSystem.drawRect(this.x, this.y , width, height);
        closeBtn.draw();
        title.draw();
    }

    float getHeight() {
        return this.height;
    }
    public boolean getMouseEvent(MouseEvent event)
    {

        //check if event is for close button
        if(!closeBtn.getMouseEvent(event))
        {
            //check if event is for title bar
            Rectangle2D.Float r = new Rectangle2D.Float(this.x,this.y,this.width,this.height);
            switch(event.getType())
            {
                 case PRESSED:
                     //call method when button pressed
                     if(r.contains(event.getX(), event.getY()))
                     {  
                         this.delegate.pressedTitleBar(event.getX(), event.getY());
                         return true;
                     }
                 break;  
                 case RELEASED:
                     //call method when button released
                     this.delegate.releasedTitleBar(event.getX(), event.getY());  
                break;
            }
            return false;
         }else{
             return false;
         }
    }
 
    
    // overriten methods for button delegate 
    @Override
    public void buttonClicked(Button btn) 
    {
        
        if(this.delegate != null) 
        {
            //check for different types of buttons
            switch(btn.type)
            {
                case CLOSE:
                    this.delegate.closeWindow();
                    break;
            }
            
        }
    }

    @Override
    public void buttonPressed(Button btn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void buttonReleased(Button btn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
