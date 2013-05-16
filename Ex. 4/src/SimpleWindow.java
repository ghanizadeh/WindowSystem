import java.awt.Color;



public class SimpleWindow extends GraphicObject
{
    /** Simple window should have following properties
     * position: x,y
     * size: width, height
    */
    public int id; 
    public float width;
    public float height;  
    public String title = "";
    public SimpleWindow(float _x, float _y, float _width, float _height)
    {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
    }        
    
    public SimpleWindow(float _width, float _height)
    {
        width = _width;
        height = _height;
    }
    
    @Override
    public void shiftPosition(float _x, float _y) 
    {
        this.x += _x;
        this.y += _y;
    }
    /** draw methods needs for drawing things in simple window*/
    @Override
    public void draw()
    {
        // draw a window here 
        this.windowSystem.setColor(Color.lightGray);
        this.windowSystem.fillRect(this.x, this.y, width, height);
        this.windowSystem.setColor(Color.black);
        this.windowSystem.drawRect(this.x, this.y , width, height);
        
    }
}
