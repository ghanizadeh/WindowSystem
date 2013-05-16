public abstract class GraphicObject
{
    WindowSystem windowSystem = WindowSystem.getInstence();
    /** Origin point, for any graphic objects
     */
    public float x;
    public float y;
    public abstract void shiftPosition(float _x,float _y);
    public abstract void draw();
    
}
