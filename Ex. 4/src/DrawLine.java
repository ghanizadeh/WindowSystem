import java.awt.Point;

public class DrawLine extends GraphicObject
{
    /** class for a line, subclasses GraphicObject
     * Has end point
     */
    private Point endPoint = new Point();
    public DrawLine(int sX, int sY, int eX, int eY)
    {
        x = sX;
        y = sY;
        endPoint.x = eX;
        endPoint.y = eY;
    }
    
    /** draw method handles drawing for DrawLine class 
     */
 
    @Override 
    public void draw()
    {
        windowSystem.drawLine(this.x,this.y, endPoint.x, endPoint.y);
    }

    @Override
    public void shiftPosition(float _x, float _y) {
        x +=_x;
        y +=_y;
    }


}
