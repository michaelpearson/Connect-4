package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener {
    private Board board;
    private Timer timer = new Timer(200, this);
    private Canvas canvas;

    private static final Color BACKGROUND_COLOR = new Color(33, 150, 243);
    private static final Color BORDER_SPACE_COLOR = Color.decode("#1565C0");
    private static final Stroke BORDER_STROKE = new BasicStroke(3);
    private static final Color EMPTY_SPACE_COLOR = Color.decode("#BBDEFB");

    private static final int HPADDING = 50;
    private static final int VPADDING = 50;
    private static final int SPACING = 10;

    private int currentPlayer = 0;

    public GameWindow(Board board) {
        this.board = board;
        setupWindow();
    }

    private void setupWindow() {
        setTitle("Connect 4");
        setSize(1024, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setIgnoreRepaint(true);
        add(canvas = new Canvas());
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.nextMove();
        canvas.repaint();
    }


    private class Canvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Rectangle r = getBounds();


            int drawableWidth = (int)r.getWidth() - HPADDING * 2;
            int drawableHeight = (int)r.getHeight() - VPADDING * 2;

            g2d.setColor(BACKGROUND_COLOR);
            g2d.fillRect(0, 0, (int)r.getWidth(), (int)r.getHeight());

            int widthCount = board.getBoardWidth();
            int heightCount = board.getBoardHeight();

            int cWidth = (drawableWidth / widthCount);
            int cHeight = (drawableHeight / heightCount);

            BoardPosition position = new BoardPosition(0,0);
            for(int x = 0; x < widthCount;x++) {
                for(int y = 0; y < heightCount; y++) {
                    Disc d = board.getPosition(position.getPosition(position, x, y));
                    Color c;
                    if(d == null) {
                        c = EMPTY_SPACE_COLOR;
                    } else {
                        c = d.getPlayerColor().getColor();
                    }

                    int size = Math.min(cWidth, cHeight) - SPACING;
                    int padding = (Math.max(cWidth, cHeight) - size) / 2;

                    if(cWidth < cHeight) {
                        drawCircle(g2d, HPADDING + x * cWidth, VPADDING + y * cHeight + padding, size, c);
                    } else {
                        drawCircle(g2d, HPADDING + x * cWidth + padding, VPADDING + y * cHeight, size, c);
                    }
                }
            }
        }

        private void drawCircle(Graphics2D g, int x, int y, int size, Color c) {
            g.setColor(c);
            g.setStroke(BORDER_STROKE);
            g.fillOval(x, y, size, size);
            g.setColor(BORDER_SPACE_COLOR);
            g.drawOval(x, y, size, size);
        }
    }
}
