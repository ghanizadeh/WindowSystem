
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Button extends GraphicObject {

    private float width;
    private float height;
    public ButtonType type; //enumeration for button
    private Color curretColor = Color.red;
    private ButtonActionDelegate delegate;
    float delta = 0.01f;

    public Button(ButtonType _type, float _x, float _y, float _width, float _height) {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        this.type = _type;
    }
    /**returns true if event is for button, otherwise returns false
     * Changes appearing of a button depending of events.
     * For example when press event received -> change color to blue
     */
    public boolean getMouseEvent(MouseEvent event) {

        //check if mouse point in rectangle of button 
        Rectangle2D.Float r = new Rectangle2D.Float(this.x, this.y, this.width, this.height);
        if (r.contains(event.getX(), event.getY())) 
        {

            switch (event.getType()) {
                case CLICKED:
                    //call method when button clicked
                    this.buttonClicked(this);

                    break;
                case PRESSED:
                    curretColor = Color.BLUE;
                    this.windowSystem.repaintRect(new Rectangle2D.Float(this.x, this.y, width, height));

                    return true;
                case RELEASED:
                    this.buttonClicked(this);
                    break;
                case DRAGGED:
                    curretColor = Color.BLUE;
                    this.windowSystem.repaintRect(new Rectangle2D.Float(this.x, this.y, width, height));

                    break;
                    

            }
            return false;
        } else {
            if (event.getType() == MouseEventType.DRAGGED) 
            {
                curretColor = Color.red;
                this.windowSystem.repaintRect(new Rectangle2D.Float(this.x, this.y, width, height));

            }
            return false;
        }
    }

    public void setButtonActionDelegate(ButtonActionDelegate delegate) {
        //adding delegate 
        this.delegate = delegate;
    }

    public void buttonClicked(Button btn) {
        // invoke delegate method
        if (this.delegate != null) {
            this.delegate.buttonClicked(btn);
        }

    }

    @Override
    public void shiftPosition(float _x, float _y) {
        this.x += _x;
        this.y += _y;
    }

    @Override
    public void draw() {

        this.windowSystem.setColor(this.curretColor);
        this.windowSystem.fillRect(this.x, this.y, width, height);
        this.windowSystem.setColor(Color.black);
        this.windowSystem.drawRect(this.x, this.y, width, height);

        //different drowing for different buttons
        switch (type) {
            case CLOSE:
                this.windowSystem.setColor(Color.black);
                this.windowSystem.drawLine(this.x + delta, this.y + delta, this.x + width - delta, this.y + height - delta);
                this.windowSystem.drawLine(this.x + delta, this.y + height - delta, this.x + width - delta, this.y + delta);

                break;
        }
    }
}
