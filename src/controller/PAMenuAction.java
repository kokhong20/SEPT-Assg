package controller;

import gui.PAMainFrame;
import gui.PAMenuBar;
import gui.PANewFileSetting;
import gui.PAProgressBar;
import gui.PASVGPanel;
import gui.PAStartMenu;
import static controller.PASelectCursorAction.elementTemp;
import gui.PADrawingItem;
import gui.PAShapeBar;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.PASVGContainer;
import model.PASVGElement;
import model.PASVGGroup;
import model.PARectangle;
import model.PALine;
import model.PACircle;
import model.PASystem;

/**
 *
 * @author LiHao
 * @since 1.1
 *
 * <p>
 * This Abstract class provide skeleton implementation for all menu action.
 * </p>
 *
 */
public abstract class PAMenuAction extends AbstractAction
{
    protected int keyMask;
    protected KeyStroke keyStroke;

    /**
     *
     * Creates a new PAMenuAction which accept a String to set menu item's name.
     *
     * @param name set menu item's name
     */
    public PAMenuAction(String name)
    {
        putValue(NAME, name);
    }

    /**
     *
     * Creates a new PAMenuAction which accept a keyEvent and String for menu
     * item's short cut and name.
     *
     * @param keyEvent set menu item's keyEvent
     * @param name set menu item's name
     */
    public PAMenuAction(int keyEvent, String name)
    {
        keyMask = PASystem.keyMask;
        keyStroke = KeyStroke.getKeyStroke(keyEvent, keyMask);

        putValue(ACCELERATOR_KEY, keyStroke);
        putValue(MNEMONIC_KEY, keyEvent);
        putValue(NAME, name);
    }

    /**
     *
     * Creates a new PAMenuAction which accept a keyEvent, event and String for
     * menu item's short cut and name.
     *
     * @param keyEvent set menu item's keyEvent
     * @param event set menu item's event
     * @param name set menu item's name
     */
    public PAMenuAction(int keyEvent, int event, String name)
    {
        keyMask = PASystem.keyMask;
        keyStroke = KeyStroke.getKeyStroke(keyEvent, keyMask | event);

        putValue(ACCELERATOR_KEY, keyStroke);
        putValue(MNEMONIC_KEY, keyEvent);
        putValue(NAME, name);
    }

    /**
     * Creates a new PAMenuAction which accept a keyEvent, event and String for
     * menu item's short cut and name, without set keyMask for menu item's.
     *
     * @param keyEvent set menu item's keyEvent
     * @param keyStroke set menu item's event
     * @param name set menu item's name
     */
    public PAMenuAction(int keyEvent, KeyStroke keyStroke, String name)
    {
        putValue(ACCELERATOR_KEY, keyStroke);
        putValue(MNEMONIC_KEY, keyEvent);
        putValue(NAME, name);
    }

    /**
     *
     * This class provide "New" menu item to create a setting frame for new
     * file.
     *
     */
    public static class NewFile extends PAMenuAction
    {
        protected static PANewFileSetting newFileSetting;
        private JDesktopPane parent;
        private PAStartMenu startMenu;

        /**
         *
         * Create a new NewFile which accept JDesktopPane as it's parent.
         *
         * @param parent set NewFile parent
         */
        public NewFile(JDesktopPane parent)
        {
            super(KeyEvent.VK_N, PASystem.getWord("New"));
            this.parent = parent;
        }

        /**
         *
         * Create a new NewFile for PAStartMenu New File button which accept
         * JDesktopPane as it's parent and PAStartMenu for program startup.
         *
         * @param parent set NewFile parent
         * @param startMenu set for program startup
         */
        public NewFile(JDesktopPane parent, PAStartMenu startMenu)
        {
            super(KeyEvent.VK_N, PASystem.getWord("New"));
            this.parent = parent;
            this.startMenu = startMenu;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (newFileSetting == null)
            {
                if (startMenu != null)
                {
                    newFileSetting = new PANewFileSetting(parent, startMenu);
                }
                else
                {
                    newFileSetting = new PANewFileSetting(parent);
                }

                newFileSetting.setClosable(false);
                parent.add(newFileSetting);
            }

            try
            {
                newFileSetting.setSelected(true);
            }
            catch (PropertyVetoException ex)
            {
                System.err.println(ex.getMessage());
            }
        }

    }

    /**
     *
     * This class provide "Open" menu item to show a file chooser frame for user
     * to select file.
     *
     */
    public static class OpenFile extends PAMenuAction
    {
        protected static JInternalFrame fcInternal;
        private JDesktopPane parent;
        private PAStartMenu startMenu;

        /**
         *
         * Create a OpenFile which accept JDesktopPane as it's parent.
         *
         * @param parent set NewFile parent
         */
        public OpenFile(JDesktopPane parent)
        {
            super(KeyEvent.VK_O, PASystem.getWord("Open"));
            this.parent = parent;
        }

        /**
         *
         * Create a new OpenFile which accept JDesktopPane as it's parent.
         *
         * @param parent set NewFile parent
         * @param startMenu set for program startup
         */
        public OpenFile(JDesktopPane parent, PAStartMenu startMenu)
        {
            super(KeyEvent.VK_O, PASystem.getWord("Open"));
            this.parent = parent;
            this.startMenu = startMenu;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (fcInternal == null)
            {
                fcInternal = new JInternalFrame(PASystem.getWord("Open"));
                JFileChooser fileChooser = new JFileChooser();
                FileFilter allFilter = new FileNameExtensionFilter("All files", "svg", "xml");
                FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
                FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
                Dimension screenResolution = PASystem.getScreenDimension();
                PAFileChooserAction fcAction;

                if (startMenu != null)
                {
                    fcAction = new PAFileChooserAction(parent, startMenu,
                            fileChooser, fcInternal);
                }
                else
                {
                    fcAction = new PAFileChooserAction(parent, fileChooser,
                            fcInternal);
                }

                fileChooser.addActionListener(fcAction);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(xmlFilter);
                fileChooser.setFileFilter(svgFilter);
                fileChooser.setFileFilter(allFilter);
                fcInternal.setClosable(false);
                fcInternal.setResizable(false);
                fcInternal.add(fileChooser);
                fcInternal.pack();

                int startX = (int) (screenResolution.getWidth() - fcInternal
                        .getWidth()) / 2;
                int startY = (int) (screenResolution.getHeight() - fcInternal
                        .getHeight()) / 2;
                Point startPoint = new Point(startX, startY);
                fcInternal.setLocation(startPoint);
                fcInternal.setVisible(true);
                parent.add(fcInternal);
            }

            try
            {
                fcInternal.setSelected(true);
            }
            catch (PropertyVetoException ex)
            {
                System.err.println(ex.getMessage());
            }
        }

    }

    /**
     *
     * This class provide "Save" menu item to save file which will show a file
     * chooser with save mode for new file or save directly with existing file.
     *
     */
    public static class SaveFile extends PAMenuAction
    {
        protected static JInternalFrame fcInternal;
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        /**
         *
         * Create a new SaveFile which accept PAMainFrame to know which
         * JInternalFrame is focus.
         *
         * @param mainFrame set which JInternalFrame is focus
         */
        public SaveFile(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_S, PASystem.getWord("Save"));
            this.onFocusFrame = mainFrame;
            this.parent = mainFrame.getParentView();
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            File svgFile = null;
            PASVGContainer container = onFocusFrame.svgPanel.svgContainer;
            /*
             * If saving file is an existing svg
             */
            if ((svgFile = container.getSvgFile()) != null)
            {
                PASaveFileChooserAction saveFileAction = new PASaveFileChooserAction(parent, null, fcInternal, container);
                saveFileAction.saveToFile(svgFile, container);
            }
            /*
             * If saving file is a new svg
             */
            else
            {
                if (fcInternal == null)
                {
                    fcInternal = new JInternalFrame(PASystem.getWord("Save"));
                    JFileChooser fileChooser = new JFileChooser();
                    FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
                    FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
                    PASaveFileChooserAction saveFileAction = new PASaveFileChooserAction(parent, fileChooser, fcInternal, container);
                    svgFile = new File(container.getFileName());
                    fileChooser.setSelectedFile(svgFile);
                    fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                    fileChooser.setFileFilter(xmlFilter);
                    fileChooser.setFileFilter(svgFilter);
                    fileChooser.addActionListener(saveFileAction);
                    fcInternal.add(fileChooser);
                    fcInternal.pack();
                    Dimension screenResolution = PASystem.getScreenDimension();
                    int startX = (int) (screenResolution.getWidth() - fcInternal
                            .getWidth()) / 2;
                    int startY = (int) (screenResolution.getHeight() - fcInternal
                            .getHeight()) / 2;
                    Point startPoint = new Point(startX, startY);
                    fcInternal.setLocation(startPoint);
                    fcInternal.setClosable(false);
                    fcInternal.setResizable(false);
                    fcInternal.setVisible(true);
                    parent.add(fcInternal);
                }

                try
                {
                    fcInternal.setSelected(true);
                }
                catch (PropertyVetoException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }
        }

    }

    /**
     *
     * This class provide "Save As" menu item to save file which will show a
     * file chooser with save mode to let user decide where to save and file
     * name they want.
     *
     */
    public static class SaveAsFile extends PAMenuAction
    {
        protected static JInternalFrame fcInternal;
        private JDesktopPane parent;
        private PAMainFrame mainFrame;

        /**
         *
         * Create a new SaveAsFile which accept PAMainFrame to know which
         * JInternalFrame is focus.
         *
         * @param mainFrame set which JInternalFrame is focus
         */
        public SaveAsFile(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_S, Event.SHIFT_MASK, PASystem.getWord("SaveAs"));
            this.mainFrame = mainFrame;
            this.parent = mainFrame.getParentView();
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            File svgFile = null;
            PASVGContainer svgContainer = mainFrame.svgPanel.svgContainer;

            if (fcInternal == null)
            {
                fcInternal = new JInternalFrame(PASystem.getWord("SaveAs"));
                JFileChooser fileChooser = new JFileChooser();
                FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
                FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
                PASaveFileChooserAction saveFileAction = new PASaveFileChooserAction(parent, fileChooser, fcInternal, svgContainer);

                if ((svgFile = svgContainer.getSvgFile()) != null)
                {
                    fileChooser.setCurrentDirectory(svgFile);
                }
                else
                {
                    svgFile = new File(svgContainer.getFileName());
                }

                fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                fileChooser.setSelectedFile(svgFile);
                fileChooser.setFileFilter(xmlFilter);
                fileChooser.setFileFilter(svgFilter);
                fileChooser.addActionListener(saveFileAction);
                fcInternal.add(fileChooser);
                fcInternal.pack();
                Dimension screenResolution = PASystem.getScreenDimension();
                int startX = (int) (screenResolution.getWidth() - fcInternal
                        .getWidth()) / 2;
                int startY = (int) (screenResolution.getHeight() - fcInternal
                        .getHeight()) / 2;
                Point startPoint = new Point(startX, startY);
                fcInternal.setLocation(startPoint);
                fcInternal.setClosable(false);
                fcInternal.setResizable(false);
                fcInternal.setVisible(true);
                parent.add(fcInternal);
            }

            try
            {
                fcInternal.setSelected(true);
            }
            catch (PropertyVetoException ex)
            {
                System.err.println(ex.getMessage());
            }
        }

    }

    /**
     *
     * This class provide "Document Properties" menu item which will show a
     * setting frame for user to set the SVG Tag's Width, Height and Unit.
     *
     */
    public static class DocumentProperties extends PAMenuAction
    {
        private JDesktopPane parent;
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new DocumentProperties which accept PAMainFrame to know
         * which JInternalFrame is focus.
         *
         * @param mainFrame set which JInternalFrame is focus
         */
        public DocumentProperties(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_D, PASystem.getWord("DocumentProperties"));
            this.parent = mainFrame.getParentView();
            this.drawPanel = mainFrame.svgPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            PASVGContainer svgContainer = drawPanel.svgContainer;
            PANewFileSetting documentSetting = new PANewFileSetting();
            documentSetting.updateDocument(svgContainer);
            documentSetting.setUpdateDocAction(svgContainer.getSvgTag(), drawPanel);

            try
            {
                documentSetting.setSelected(true);
            }
            catch (PropertyVetoException ex)
            {
                System.err.println(ex.getMessage());
            }

            parent.add(documentSetting);
            documentSetting.toFront();
        }

    }

    /**
     *
     * This class provide "Exit" menu item which will show a confirmation dialog
     * for user to confirm want to exit the program.
     *
     */
    public static class ExitProgram extends PAMenuAction
    {
        private JDesktopPane parent;

        /**
         *
         * Create a new ExitProgram which accept JDesktopPane as it's parent.
         *
         * @param parent set ExitProgram parent
         */
        public ExitProgram(JDesktopPane parent)
        {
            super(KeyEvent.VK_W, PASystem.getWord("Exit"));
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int selectedOption = JOptionPane.showConfirmDialog(parent,
                    "Are You Sure You Want To Exit?", PASystem.getWord("Exit"),
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (selectedOption == 0)
            {
                System.exit(0);
            }
        }

    }

    /**
     *
     * This class provide "Select All" menu item which will select all the
     * element inside the PASVGPanel that the PAMainFrame is focused.
     *
     */
    public static class SelectAll extends PAMenuAction
    {
        private PAMainFrame mainFrame;

        /**
         *
         * Create a new SelectAll which accept PAMainFrame to know which
         * JInternalFrame is focus.
         *
         * @param mainFrame set which JInternalFrame is focus
         */
        public SelectAll(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_A, PASystem.getWord("SelectAll"));
            this.mainFrame = mainFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JToggleButton virtualButton = new JToggleButton();
            PASVGPanel drawPanel = mainFrame.svgPanel;
            PASelectCursorAction selectAllAction = new PASelectCursorAction(
                    drawPanel, virtualButton, mainFrame.attributeBar);
            
            elementTemp.addAll(drawPanel.elementCollection);
            for (int i = 0; i < drawPanel.elementCollection.size(); i++)
            {
                PASVGElement element = drawPanel.elementCollection.get(i);
                selectAllAction.drawBoundsChecking(element);
            }
            drawPanel.repaint();
        }

    }

    /**
     *
     * This class provide "Deselect All" menu item which will deselect all the
     * element inside the PASVGPanel that the PAMainFrame is focused.
     *
     */
    public static class DeselectAll extends PAMenuAction
    {
        private PAMainFrame mainFrame;

        /**
         *
         * Create a new DeselectAll which accept PAMainFrame to know which
         * JInternalFrame is focus.
         *
         * @param mainFrame set which JInternalFrame is focus
         */
        public DeselectAll(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_A, Event.SHIFT_MASK, PASystem.getWord("DeselectAll"));
            this.mainFrame = mainFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            PASVGPanel drawPanel = mainFrame.svgPanel;
            PASelectCursorAction.elementTemp.clear();
            drawPanel.reDrawImage(drawPanel.getScale());
        }

    }

    /**
     *
     * This class provide "Cut" menu item which will allow user to cut the
     * selected elements inside the PASVGPanel.
     *
     */
    public static class Cut extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new Cut which accept PASVGPanel to know which PASVGPanel's
         * elements want to perform cut action.
         *
         * @param drawPanel set which PASVGPanel want to perform cut action
         */
        public Cut(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_X, PASystem.getWord("Cut"));
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
            LinkedList<PASVGElement> eleList = new LinkedList<>();
            eleList.addAll(PASelectCursorAction.elementTemp);
            Paste.storeElement = eleList;
            mainList.removeAll(eleList);

            drawPanel.reDrawImage(drawPanel.getScale());
        }

    }

    /**
     *
     * This class provide "Copy" menu item which will allow user to copy the
     * selected elements inside the PASVGPanel.
     *
     */
    public static class Copy extends PAMenuAction
    {
        /**
         *
         * Create a new Copy.
         *
         */
        public Copy()
        {
            super(KeyEvent.VK_C, PASystem.getWord("Copy"));
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> eleList = new LinkedList<>();
            eleList.addAll(PASelectCursorAction.elementTemp);
            Paste.storeElement = eleList;
        }

    }

    /**
     *
     * This class provide "Paste" menu item which will allow user to paste the
     * copied or cut elements to on focusing PASVGPanel.
     *
     */
    public static class Paste extends PAMenuAction
    {
        public static LinkedList<PASVGElement> storeElement;
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new Paste which accept PASVGPanel to know which PASVGPanel
         * to perform paste action.
         *
         * @param drawPanel set which PASVGPanel want to perform paste action
         */
        public Paste(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_V, PASystem.getWord("Paste"));
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;

            for (int index = storeElement.size() - 1; index >= 0; index--)
            {
                PASVGElement element = storeElement.get(index);

                if (mainList.contains(element))
                {
                    cloneElement(element, mainList);
                }
                else
                {
                    mainList.add(element);
                }
            }

            drawPanel.reDrawImage(drawPanel.getScale());
        }

        private void cloneElement(PASVGElement element, LinkedList<PASVGElement> mainList)
        {
            PASVGElement cloneEle = null;

            if (element instanceof PALine)
            {
                PALine o = (PALine) element;
                cloneEle = new PALine(o.getStroke(), o.getStrokeWidth(),
                        o.getX1() + 5, o.getX2() + 5, o.getY1() + 5, o.getY2() + 5);
            }
            else if (element instanceof PACircle)
            {
                PACircle o = (PACircle) element;
                cloneEle = new PACircle(o.getFill(), o.getStroke(),
                        o.getStrokeWidth(), o.getCx() + 5, o.getCy() + 5, o.getR());
            }
            else if (element instanceof PARectangle)
            {
                PARectangle o = (PARectangle) element;
                cloneEle = new PARectangle(o.getFill(), o.getStroke(),
                        o.getStrokeWidth(), o.getX() + 5, o.getY() + 5, o.getWidth(), o.getHeight());
            }
            else if (element instanceof PASVGGroup)
            {
                PASVGGroup o = (PASVGGroup) element;
                cloneEle = new PASVGGroup(o.getGroupElementList(), o.getFill(), o.getStroke(),
                        o.getStrokeWidth(), o.getParentGroup(), o.getId());
            }

            if (cloneEle != null)
            {
                mainList.add(cloneEle);
            }
        }

    }

    /**
     *
     * This class provide "Delete" menu item which will allow user to delete
     * selected elements inside the PASVGPanel.
     *
     */
    public static class Delete extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new Delete which accept PASVGPanel to know which
         * PASVGPanel's elements want to perform delete action.
         *
         * @param drawPanel set which PASVGPanel want to perform delete action
         */
        public Delete(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_BACK_SPACE, KeyStroke.getKeyStroke(
                    KeyEvent.VK_BACK_SPACE, 0), PASystem.getWord("Delete"));
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
            LinkedList<PASVGElement> selectedList = PASelectCursorAction.elementTemp;

            if (selectedList != null)
            {
                mainList.removeAll(selectedList);
                drawPanel.reDrawImage(drawPanel.getScale());
            }
        }

    }

    /**
     *
     * This class provide "Zoom In" menu item which will allow user to zoom in
     * the PASVGPanel.
     *
     */
    public static class ZoomIn extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new ZoomIn which accept PASVGPanel to know which PASVGPanel
         * want to perform zoom in action.
         *
         * @param drawPanel set which PASVGPanel want to perform zoom in action
         */
        public ZoomIn(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_PLUS, PASystem.getWord("ZoomIn"));
            this.drawPanel = drawPanel;
        }

        public ZoomIn(PASVGPanel drawPanel, JButton button)
        {
            super(KeyEvent.VK_PLUS, "");
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            double scale = drawPanel.getScale();
            DecimalFormat df = new DecimalFormat("##.#");
            scale = Double.parseDouble(df.format(scale));

            if (scale < 10)
            {
                if (scale > 2)
                {
                    scale += 0.5;
                }
                else
                {
                    scale += 0.1;
                }
            }

            drawPanel.zoomInOutSVG(scale);
        }

    }

    /**
     *
     * This class provide "Zoom In" menu item which will allow user to zoom out
     * the PASVGPanel.
     *
     */
    public static class ZoomOut extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new ZoomOut which accept PASVGPanel to know which PASVGPanel
         * want to perform zoom out action.
         *
         * @param drawPanel set which PASVGPanel want to perform zoom out action
         */
        public ZoomOut(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_MINUS, PASystem.getWord("ZoomOut"));
            this.drawPanel = drawPanel;
        }

        public ZoomOut(PASVGPanel drawPanel, JButton button)
        {
            super(KeyEvent.VK_MINUS, "");
            this.drawPanel = drawPanel;
            //this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            double scale = drawPanel.getScale();
            DecimalFormat df = new DecimalFormat("##.#");
            scale = Double.parseDouble(df.format(scale));

            if (scale > 0.1)
            {
                if (scale > 2)
                {
                    scale -= 0.5;
                }
                else
                {
                    scale -= 0.1;
                }
            }

            drawPanel.zoomInOutSVG(scale);
        }

    }

    /**
     *
     * This class provide "Actual Size" menu item which will allow user to set
     * the PASVGPanel back to actual size.
     *
     */
    public static class ActualSize extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new ActualSize which accept PASVGPanel to know which
         * PASVGPanel want to perform actual size action.
         *
         * @param drawPanel set which PASVGPanel want to perform actual size
         * action
         */
        public ActualSize(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_0, PASystem.getWord("ActualSize"));
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            double scale = 1.0;
            drawPanel.zoomInOutSVG(scale);
        }

    }

    /**
     *
     * This class provide "Group" menu item and "Group" button on PADrawingKit
     * which will allow user to group a set of selected elements inside the
     * PASVGPanel.
     *
     */
    public static class GroupAction extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new GroupAction which accept PASVGPanel to know which
         * PASVGPanel's elements want to perform group action.
         *
         * @param drawPanel set which PASVGPanel want to perform group action
         */
        public GroupAction(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_G, PASystem.getWord("Group"));
            this.drawPanel = drawPanel;
        }

        /**
         *
         * Create a new GroupAction which accept PASVGPanel to know which
         * PASVGPanel's elements want to perform group action.
         *
         * @param drawPanel set which PASVGPanel want to perform group action
         * @param button set the JButton to show it is for PADrawingKitItem
         */
        public GroupAction(PASVGPanel drawPanel, JButton button)
        {
            super(KeyEvent.VK_G, "");
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
            LinkedList<PASVGElement> elementList = new LinkedList<>();
            elementList.addAll(PASelectCursorAction.elementTemp);
            PASVGGroup newGroup = new PASVGGroup(elementList);
            LinkedList<PASVGElement> groupList = newGroup
                    .getGroupElementList();
            mainList.addLast(newGroup);
            mainList.removeAll(groupList);

            for (int index = groupList.size() - 1; index >= 0; index--)
            {
                groupList.get(index).setGrouped(true);
                groupList.get(index).setParentGroup(newGroup);
            }

            drawPanel.drawToImage();
            drawPanel.repaint();
        }

    }

    /**
     *
     * This class provide "Ungroup" menu item and "Ungroup" button on
     * PADrawingKit which will allow user to ungroup a selected group element
     * inside the PASVGPanel.
     *
     */
    public static class UnGroupAction extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        /**
         *
         * Create a new UnGroupAction which accept PASVGPanel to know which
         * PASVGPanel's elements want to perform ungroup action.
         *
         * @param drawPanel set which PASVGPanel want to perform ungroup action
         */
        public UnGroupAction(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_G, Event.SHIFT_MASK, PASystem.getWord("Ungroup"));
            this.drawPanel = drawPanel;
        }

        /**
         *
         * Create a new UnGroupAction which accept PASVGPanel to know which
         * PASVGPanel's elements want to perform ungroup action.
         *
         * @param drawPanel set which PASVGPanel want to perform ungroup action
         * @param button set the JButton to show it is for PADrawingKitItem
         */
        public UnGroupAction(PASVGPanel drawPanel, JButton button)
        {
            super(KeyEvent.VK_G, Event.SHIFT_MASK, "");
            this.drawPanel = drawPanel;
            //this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> elementList = PASelectCursorAction.elementTemp;

            if (elementList.size() == 1 && elementList.getFirst() instanceof PASVGGroup)
            {
                PASVGGroup selectedGroup = (PASVGGroup) elementList.getFirst();
                LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
                LinkedList<PASVGElement> groupList = selectedGroup
                        .getGroupElementList();

                for (int index = mainList.size() - 1; index >= 0; index--)
                {
                    PASVGElement element = mainList.get(index);

                    if (element == selectedGroup)
                    {
                        mainList.remove(index);

                        for (int j = groupList.size() - 1; j >= 0; j--)
                        {
                            groupList.get(j).setGrouped(false);
                            groupList.get(j).setParentGroup(null);
                        }

                        mainList.addAll(index, groupList);
                    }
                }
            }

            drawPanel.drawToImage();
            drawPanel.repaint();
        }

    }

    /**
     *
     * This class for removing action on menu item when action is not necessary.
     *
     */
    public static class RemoveAction extends PAMenuAction
    {
        /**
         *
         * Create new RemoveAction which accept a keyEvent and String for menu
         * item's short cut and name.
         *
         * @param keyEvent set menu item's keyEvent
         * @param name set menu item's name
         */
        public RemoveAction(int keyEvent, String name)
        {
            super(keyEvent, name);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
        }

    }

    /**
     *
     * This class for removing action on menu item which with event when action
     * is not necessary.
     *
     */
    public static class RemoveEventAction extends PAMenuAction
    {
        /**
         *
         * Create new RemoveAction which accept a keyEvent, event and String for
         * menu item's short cut and name.
         *
         * @param keyEvent set menu item's keyEvent
         * @param event set menu item's event
         * @param name set menu item's name
         */
        public RemoveEventAction(int keyEvent, int event, String name)
        {
            super(keyEvent, event, name);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
        }

    }

    /**
     *
     * This class for changing menu item's language.
     *
     */
    public static class LanguageAction extends PAMenuAction
    {
        private PAMenuBar menuBar;
        private String languageToSet;
        private JDesktopPane parent;

        /**
         *
         * Create a new LanguageAction which accept PAMenuBar for getting menu
         * item and a String to decide which language to change
         *
         * @param menuBar set PAMenuBar want to change language
         * @param languageToSet set language want to change
         */
        public LanguageAction(PAMenuBar menuBar, String languageToSet, JDesktopPane parent)
        {
            super(languageToSet);
            this.menuBar = menuBar;
            this.languageToSet = languageToSet;
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // TODO Auto-generated method stub
            Thread t1 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    PASystem.setCurrentResource(languageToSet, parent);
                    /*
                     * e.GetSource() returns a null, dunno why?
                     */
                    menuBar.resetText();
                    PADrawingItem.resetDrawingItemText();
                    PAShapeBar.resetShapeBarText();
                }

            });
            t1.start();

        }

    }
}
