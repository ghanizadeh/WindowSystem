
import java.awt.Color;
import java.awt.Font;


public class DrawString extends GraphicObject
{

    private String text;
    private Font font;
    public String getText()
    {
        return text;
    }
     public void setText(String txt)
    {
        this.text = txt;
    }
     public DrawString(String _text, float _x, float _y)
    {
        text = _text;
        x = _x;
        y = _y;
        font = new Font ("TimesRoman", Font.BOLD, 15);
    }
    @Override
    public void draw() 
    {
        this.windowSystem.setColor(Color.darkGray);
        this.windowSystem.setFont(font);
        this.windowSystem.drawString(this.text,this.x,this.y);
    }

    @Override
    public void shiftPosition(float _x, float _y)
    {
        this.x +=_x;
        this.y += _y;
    }

}
