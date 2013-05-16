/** class for unification mouse events, in order to pass them 
 * to different objects
 */
public class MouseEvent {
    private MouseEventType type;
    private float x;
    private float y;
    public float getX(){return this.x;}
    public float getY(){return this.y;}
    public MouseEventType getType(){return this.type;}
    public MouseEvent(MouseEventType _type, float _x, float _y)
    {
        this.type = _type;
        this.x = _x;
        this.y = _y;
    }
    
}
