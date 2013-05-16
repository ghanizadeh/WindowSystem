public interface TitleBarDelegate 
{
    public void closeWindow();
    public void minWindow();
    public void maxWindow();
    public void pressedTitleBar(float x, float y);
    public void releasedTitleBar(float x, float y);
}
