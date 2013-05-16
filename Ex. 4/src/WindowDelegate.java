public interface WindowDelegate 
{
    public void closeWindow(WindowDressing wd);
    public void minWindow(WindowDressing wd);
    public void maxWindow(WindowDressing wd);
    public void resizeWindow(WindowDressing wd);
    public void pressDragWindow(WindowDressing wd, float x, float y);
    public void releaseDragWindow(WindowDressing wd, float x, float y);      
}
