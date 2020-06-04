package GUI;

import javax.swing.*;
import java.awt.*;

public class CalorieCheck_Pane extends JPanel {

  //Segment JPanel
  private JPanel secondSegBody;
  private JPanel firstSegBody;

  //Image Label owned by Segment Panel
  private JLabel secondSegs[]; // Second Segment. 10 components
  private JLabel firstSegs[]; // First Segment. 8 components
  private JLabel clockLabel; //clock icon

  //text label owned by bodyPanel
  private JLabel ccLabel; // World Time text

  public CalorieCheck_Pane() {

    setVisible(true);
    setSize(400,240);
    setLocation(45, 30);
    setBackground(Color.white);
    setLayout(null);

    //initialize & draw segment JPanel
    secondSegBody = new JPanel(){
      public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        g2.setStroke(new BasicStroke(3));

        g2.drawRoundRect(2, 2, this.getWidth()-4, this.getHeight()-4, 20, 20);

      }
    };
    firstSegBody = new JPanel(){
      public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        g2.setStroke(new BasicStroke(3));

        g2.drawRoundRect(2, 2, this.getWidth()-4, this.getHeight()-4, 20, 20);

      }
    };

    //initialize second segment labels
    secondSegs = new JLabel[10];
    for(int i=0; i<10; i++) secondSegs[i] = new JLabel();

    //initialize first segment labels
    firstSegs = new JLabel[8];
    for(int i=0; i<8; i++) firstSegs[i] = new JLabel();

    //Clock Icon
    clockLabel = new JLabel();

    //'Calorie' Text Label
    ccLabel = new JLabel("CALORIE");

    //second seg panel Info
    secondSegBody.setSize(205, 55);
    secondSegBody.setLocation(25,35);
    secondSegBody.setBackground(Color.white);
    secondSegBody.setLayout(new GridLayout(1, 10, 0, 0));
    secondSegBody.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Internal margin

    //first seg panel info
    firstSegBody.setSize(360, 95);
    firstSegBody.setLocation(20, 120);
    firstSegBody.setBackground(Color.white);
    firstSegBody.setLayout(new GridLayout(1, 8, 0, 0));
    firstSegBody.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Internal margin

    //Clock
    clockLabel.setSize(50, 50);
    clockLabel.setLocation(this.getWidth() - 80, secondSegBody.getLocation().y - 2);

    //CALORIE text component
    ccLabel.setFont(new Font("Open Sans", Font.BOLD, 10));
    ccLabel.setHorizontalAlignment(SwingConstants.LEFT);
    ccLabel.setBounds((secondSegBody.getLocation().x+secondSegBody.getWidth()) + 5, secondSegBody.getLocation().y + 25, 60, 21);

    //Component adding
    add(firstSegBody);
    add(secondSegBody);
    add(clockLabel);
    add(ccLabel);


    for(int i=0; i<10; i++){
      secondSegBody.add(secondSegs[i]);
    }

    for(int i=0; i<8; i++){
      firstSegBody.add(firstSegs[i]);
    }

    this.repaint();
    this.revalidate();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D)g;
    RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2.setRenderingHints(qualityHints);
    g2.setStroke(new BasicStroke(3));

    g2.drawRoundRect(2, 2, this.getWidth()-5, this.getHeight()-5, 40, 40);

  }

  public JLabel[] getSecondSegs() { return secondSegs; }

  public void setSecondSegs(JLabel[] secondSegs) { this.secondSegs = secondSegs; }

  public JLabel[] getFirstSegs() { return firstSegs; }

  public void setFirstSegs(JLabel[] firstSegs) { this.firstSegs = firstSegs; }

  public JLabel getClockLabel() { return clockLabel; }

  public void setClockLabel(JLabel clockLabel) { this.clockLabel = clockLabel; }

}
