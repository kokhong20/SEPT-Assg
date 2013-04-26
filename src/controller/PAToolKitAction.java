package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gui.PASVGPanel;
import gui.PAShapeBar;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import model.PAColor;
import model.PARectangle;
import model.PASVGContainer;
import model.PASVGElement;
import model.PAUnit;

public abstract class PAToolKitAction extends AbstractAction {
	protected JButton button;
	protected PASVGPanel drawPanel;
	protected BufferedImage drawImage;
	protected LinkedList<PASVGElement> elementCollection;
	protected PAShapeBar shapeBar;
	protected Color fill, stroke;
	protected double strokeWidth;

	public PAToolKitAction(PASVGPanel drawPanel, JButton button,
			PAShapeBar shapeBar) {
		this.button = button;
		this.drawPanel = drawPanel;
		this.shapeBar = shapeBar;
		elementCollection = drawPanel.svgContainer.getSvgContainer();

		button.setAction(this);
	}

	public void setShapeAttributes() {
		fill = shapeBar.fillButton.isEnabled() ? shapeBar.fillButton
				.getBackground() : PAColor.DEFAULT_FILL;
		stroke = shapeBar.strokeButton.isEnabled() ? shapeBar.strokeButton
				.getBackground() : PAColor.DEFAULT_FILL;
		strokeWidth = shapeBar.strokeWidthBox.isEnabled() ? (double) shapeBar.strokeWidthBox
				.getValue() : PAUnit.DEFAULT_STROKE_WIDTH;
	}

	public abstract void addActionToComponents();

	public static class DrawRectangle extends PAToolKitAction implements
			MouseMotionListener, MouseListener {
		Point startDrag, endDrag;

		public DrawRectangle(PASVGPanel drawPanel, JButton button,
				PAShapeBar shapeBar) {
			super(drawPanel, button, shapeBar);
		}

		@Override
		public void addActionToComponents() {
			drawPanel.addMouseListener(this);
			drawPanel.addMouseMotionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			addActionToComponents();
		}

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == drawPanel) {
				startDrag = new Point(e.getX(), e.getY());
				endDrag = startDrag;
				drawPanel.repaint();
			}

		}

		public void mouseReleased(MouseEvent e) {
			PASVGElement rect = makeRectangle(fill, stroke, strokeWidth,
					startDrag.x, startDrag.y, e.getX(), e.getY());
			elementCollection.add(rect);
			startDrag = null;
			endDrag = null;
			drawPanel.repaint();
		}

		private PARectangle makeRectangle(Color fill, Color stroke,
				double strokeWidth, int x1, int y1, int x2, int y2) {
			return new PARectangle(fill, stroke, strokeWidth,
					(double) Math.min(x1, x2), (double) Math.min(y1, y2),
					(double) Math.abs(x1 - x2), (double) Math.abs(y1 - y2));

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			endDrag = new Point(e.getX(), e.getY());
			drawPanel.repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}
}
