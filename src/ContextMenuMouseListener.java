package theComputationOfTheRootsOfAnyEquation;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

public class ContextMenuMouseListener extends MouseAdapter {
	
	private final static String CUTTEXT = "Вырезать";
	private final static String SELECTALLTEXT = "Выделить все";
	private final static String COPYTEXT = "Копировать";
	private final static String PASTETEXT = "Вставить";

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			if (!(e.getSource() instanceof JTextComponent)) {
				return;
			}
			final JTextComponent textComponent = (JTextComponent) e.getSource();
			textComponent.requestFocus();
			final boolean enabled = textComponent.isEnabled();
			final boolean editable = textComponent.isEditable();
			final boolean nonempty = !(textComponent.getText() == null || textComponent.getText().equals(""));
			final boolean marked = textComponent.getSelectedText() != null;
			final boolean pasteAvailable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).isDataFlavorSupported(DataFlavor.stringFlavor);
			final JPopupMenu popup = new JPopupMenu();
			if (enabled && editable && marked) {
				Action cutAction = textComponent.getActionMap().get(DefaultEditorKit.cutAction);
				if (cutAction == null) {
					cutAction = textComponent.getActionMap().get("cut");
				}
				if (cutAction != null) {
					popup.add(cutAction).setText(CUTTEXT);
				}
			}
			if (enabled && marked) {
				Action copyAction = textComponent.getActionMap().get(DefaultEditorKit.copyAction);
				if (copyAction == null) {
					copyAction = textComponent.getActionMap().get("copy");
				}
				if (copyAction != null) {
					popup.add(copyAction).setText(COPYTEXT);
				}
			}
			if (enabled && editable && pasteAvailable) {
				Action pasteAction = textComponent.getActionMap().get(DefaultEditorKit.pasteAction);
				if (pasteAction == null) {
					pasteAction = textComponent.getActionMap().get("paste");
				}
				if (pasteAction != null) {
					popup.add(pasteAction).setText(PASTETEXT);
				}
			}
			if (enabled && nonempty) {
				Action selectAllAction = textComponent.getActionMap().get(DefaultEditorKit.selectAllAction);
				if (selectAllAction == null) {
					selectAllAction = textComponent.getActionMap().get("selectAll");
				}
				if (selectAllAction != null) {
					if (popup.getComponentCount() > 0) {
						if (!(popup.getComponent(popup.getComponentCount() - 1) instanceof JPopupMenu.Separator)) {
							popup.addSeparator();
						}
					}
					popup.add(selectAllAction).setText(SELECTALLTEXT);
				}
			}
			if (popup.getComponentCount() > 0) {
				if (popup.getComponent(0) instanceof JPopupMenu.Separator) {
					popup.remove(0);
				}
				if (popup.getComponent(popup.getComponentCount() - 1) instanceof JPopupMenu.Separator) {
					popup.remove(popup.getComponentCount() - 1);
				}
				popup.show(e.getComponent(), e.getX(), e.getY() - popup.getSize().height);

			}
		}
	}
}

