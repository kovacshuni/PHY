package com.hunorkovacs.phy;

import java.text.DecimalFormat;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PHYView extends JFrame
{
    private PHYModel phymodel;
    
    private javax.swing.JButton createBButton;
    private javax.swing.JButton createGFButton;
    private javax.swing.JPanel createPanel;
    private javax.swing.JButton createRISButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel editPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList nameList;
    private DrawPanel drawPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton playButton;
    private javax.swing.JPanel sliderPanel;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel speedLabel;    

    
    public PHYView(PHYModel m)    
    {
        this.phymodel = m;
        initComponents();
    }

    private void initComponents()
    {

        jToolBar1 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        speedLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        playButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        sliderPanel = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        drawPanel = new DrawPanel(phymodel, this);
        editPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nameList = new javax.swing.JList(phymodel.getListModel());
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        createPanel = new javax.swing.JPanel();
        createBButton = new javax.swing.JButton();
        createRISButton = new javax.swing.JButton();
        createGFButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHY");
        setMinimumSize(new java.awt.Dimension(800, 800));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Time Speed:");
        jLabel2.setMaximumSize(new java.awt.Dimension(90, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(90, 14));
        jToolBar1.add(jLabel2);

        speedLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        speedLabel.setText("1.000x");
        jToolBar1.add(speedLabel);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Time:");
        jLabel1.setMaximumSize(new java.awt.Dimension(50, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(50, 14));
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 14));
        jToolBar1.add(jLabel1);

        timeLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        timeLabel.setText("0:00:00.000");
        timeLabel.setMaximumSize(new java.awt.Dimension(75, 14));
        timeLabel.setMinimumSize(new java.awt.Dimension(75, 14));
        timeLabel.setPreferredSize(new java.awt.Dimension(75, 14));
        jToolBar1.add(timeLabel);

        playButton.setBackground(new java.awt.Color(0, 0, 0));
        playButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        playButton.setForeground(new java.awt.Color(255, 255, 255));
        playButton.setText("Play");
        playButton.setFocusable(false);
        playButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playButton.setMaximumSize(new java.awt.Dimension(100, 25));
        playButton.setMinimumSize(new java.awt.Dimension(100, 25));
        playButton.setPreferredSize(new java.awt.Dimension(100, 25));
        playButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(playButton);

        stopButton.setBackground(new java.awt.Color(0, 0, 0));
        stopButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stopButton.setForeground(new java.awt.Color(255, 255, 255));
        stopButton.setText("Stop");
        stopButton.setFocusable(false);
        stopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(stopButton);

        sliderPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Time Speed"));
        sliderPanel.setMaximumSize(new java.awt.Dimension(250, 100));
        sliderPanel.setMinimumSize(new java.awt.Dimension(250, 100));
        sliderPanel.setPreferredSize(new java.awt.Dimension(250, 100));

        jSlider1.setMajorTickSpacing(700);
        jSlider1.setMaximum(700);
        jSlider1.setMinimum(-700);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(0);

        javax.swing.GroupLayout sliderPanelLayout = new javax.swing.GroupLayout(sliderPanel);
        sliderPanel.setLayout(sliderPanelLayout);
        sliderPanelLayout.setHorizontalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );
        sliderPanelLayout.setVerticalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        drawPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
        );

        editPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit/Delete"));
        editPanel.setMaximumSize(new java.awt.Dimension(262, 32767));
        editPanel.setMinimumSize(new java.awt.Dimension(262, 376));

        nameList.setMaximumSize(new java.awt.Dimension(33, 1000));
        nameList.setPreferredSize(new java.awt.Dimension(33, 1000));
        jScrollPane1.setViewportView(nameList);

        editButton.setText("Edit...");

        deleteButton.setText("Delete");

        javax.swing.GroupLayout editPanelLayout = new javax.swing.GroupLayout(editPanel);
        editPanel.setLayout(editPanelLayout);
        editPanelLayout.setHorizontalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(deleteButton)))
                .addContainerGap())
        );
        editPanelLayout.setVerticalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addContainerGap())
        );

        createPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Create"));
        createPanel.setMaximumSize(new java.awt.Dimension(262, 317));
        createPanel.setMinimumSize(new java.awt.Dimension(262, 317));

        createBButton.setText("Pointlike Body");

        createRISButton.setText("Rectangular Isolator Space");

        createGFButton.setText("Gravity Field");

        javax.swing.GroupLayout createPanelLayout = new javax.swing.GroupLayout(createPanel);
        createPanel.setLayout(createPanelLayout);
        createPanelLayout.setHorizontalGroup(
            createPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createRISButton, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(createBButton, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(createGFButton, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap())
        );
        createPanelLayout.setVerticalGroup(
            createPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPanelLayout.createSequentialGroup()
                .addComponent(createBButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createRISButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createGFButton)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sliderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }

    public DrawPanel getDrawPanel()
    {
        return drawPanel;
    }
    
    public int[] getSelectedListIndices()
    {
        return nameList.getSelectedIndices();
    }
    
    /**
     * Computes the hours, minutes, seconds and milliseconds from milliseconds retured by the timer, and dispalys is in the toolbar.
     */
    public void reclock()
    {
        long t;
        int h, m, s, ss;
        String out = new String();
        
        t = phymodel.getElapsedTime();
        h = (int)(t/3600000);
        m = (int)((t-h*3600000)/60000);
        s = (int)((t-h*3600000-m*60000)/1000);
        ss = (int)(t-h*3600000-m*60000-s*1000);
        
        out = (new Integer(h)).toString();
        if (m<10) out = out + ":0" + m;
        else out = out + ":" + m;
        if (s<10) out = out + ":0" + s + ".";
        else out = out + ":" + s + ".";
        if (ss<100) out = out + "0";
        if (ss<10) out = out + "0";
        out = out + ss;
        timeLabel.setText(out);
    }   
    
    public void updateTimeSpeed()
    {
        speedLabel.setText(strPre(phymodel.getTimeSpeed()) + "x");
    }
    
    public void setPlayButton(boolean q)
    {
        if (q) playButton.setText("Play");
        else playButton.setText("Pause");
    }
    
    public void setPlayButtonEnabled(boolean q)
    {
        playButton.setEnabled(q);
    }
    
    public void setStopButton(boolean q)
    {
        stopButton.setEnabled(q);
    }
       
    public void setDeleteButton(boolean q)
    {
        deleteButton.setEnabled(q);
    }

    public void setEditButton(boolean q)
    {
        editButton.setEnabled(q);
    } 
    
    public void setCreateBButton(boolean q)
    {
        createBButton.setEnabled(q);
    }
    
    public void setCreateGFButton(boolean q)
    {
        createGFButton.setEnabled(q);
    }
    
    public void setCreateRISButton(boolean q)
    {
        createRISButton.setEnabled(q);
    }    
    
    public void moveOffset(int x, int y)
    {
        drawPanel.moveOffset(x,y);
    }
    
    public void setRefCoord(int x, int y)
    {
        drawPanel.setRefCoord(x,y);
    }
    
    public void scrollZoom(int k)
    {
        drawPanel.scrollZoom(k);
    }    
    
    public com.hunorkovacs.phy.Dimension getOffset()
    {
        return drawPanel.getOffset();
    }
    
    public com.hunorkovacs.phy.Dimension getDimension()
    {
        return drawPanel.getDimension();
    }
    
    public double getZoom()
    {
        return drawPanel.getZoom();
    }
    
    public int getSliderValue()
    {
        return jSlider1.getValue();
    }
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    public void addPlayListener(ActionListener al)
    {
        playButton.addActionListener(al);
    }
    
    public void addStopListener(ActionListener al)
    {
        stopButton.addActionListener(al);
    }
    
    public void addDeleteListener(ActionListener al)
    {
        deleteButton.addActionListener(al);
    }    
    
    public void addEditListener(ActionListener al)
    {
        editButton.addActionListener(al);
    }
       
    public void addCreateBListener(ActionListener al)
    {
        createBButton.addActionListener(al);
    }    
    
    public void addCreateGFListener(ActionListener al)
    {
        createGFButton.addActionListener(al);
    }
    
    public void addCreateRISListener(ActionListener al)
    {
        createRISButton.addActionListener(al);
    }    
    
    public void addSelectListener(ListSelectionListener lsl)
    {
        nameList.addListSelectionListener(lsl);
    }
    
    public void addDrawPanelMouseMotionListener(MouseMotionListener ml)
    {
        drawPanel.addMouseMotionListener(ml);
    }

    public void addDrawPanelMouseListener(MouseListener cl)
    {
        drawPanel.addMouseListener(cl);
    }    
    
    public void addDrawPanelMouseWheelListener(MouseWheelListener wl)
    {
        drawPanel.addMouseWheelListener(wl);
    }    
    
    public void addTimeSpeedListener(ChangeListener cl)
    {
        jSlider1.addChangeListener(cl);
    }
    
    public void redraw()
    {
        drawPanel.repaint();
    }    
    
    /**
     * Converts a decimal number to a string representing that number with 3 digit precision after the decimal point.
     * @param inValue the decimal number to be converted
     * @return the string holding the real number with 3 decimal digit precision
     */
    private String strPre(double inValue)
    {
        DecimalFormat threeDec = new DecimalFormat("0.000");
        threeDec.setGroupingUsed(false);
        return threeDec.format(inValue).replace(',','.');
    }     
}
