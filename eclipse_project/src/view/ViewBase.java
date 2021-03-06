package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import common.Constants;
import controller.ControllerBase;
import model.ModelBase;

public class ViewBase extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    
    public ViewBase () {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Constants.APP_ICON);
        setPreferredSize(Constants.VIEW_SIZE);
    }

    public void showUp() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Show dialog with content and icon
     */
    private void notice(String content, int mode) {
        String title = "";
        switch (mode) {
            case JOptionPane.WARNING_MESSAGE: title = "Cảnh báo"; break;
            case JOptionPane.ERROR_MESSAGE: title = "Lỗi!"; break;
            case JOptionPane.QUESTION_MESSAGE: title = "?"; break;
            case JOptionPane.INFORMATION_MESSAGE: title = "Thông báo"; break;
            default : title = "Thông điệp"; break;
        }
        JOptionPane.showMessageDialog(
                this,
                content,
                title,
                mode
        );
    }

    /**
     * Show warning dialog
     */
    protected void noticeWarning(String content) {
        notice(content, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Show information dialog
     */
    protected void noticeInfo(String content) {
        notice(content, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show error dialog
     */
    protected void noticeError(String content) {
        notice(content, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show dialog based on value of param
     */
    protected void noticeResult(
            boolean rs,
            String inCaseTrue,
            String inCaseFalse
    ) {
        if (rs) {
            noticeInfo(inCaseTrue);
        } else {
            noticeError(inCaseFalse);
        }
    }
   
   public void setController(ControllerBase<ModelBase, ViewBase> c) {} 
    
    @Override
    public void update(Observable o, Object arg) {
    }
}
