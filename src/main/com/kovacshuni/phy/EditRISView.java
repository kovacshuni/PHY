package com.kovacshuni.phy;

/**
 * This is a window where the user can edit the properties of a RectangularIsolatorSpace object.
 */
public class EditRISView extends EditXView
{
    private javax.swing.JLabel dxLabel;
    private javax.swing.JLabel dyLabel;    
    
    protected javax.swing.JTextField dxField;    
    protected javax.swing.JTextField dyField;
    
    private javax.swing.JSeparator jSeparator3;      

    /**
     * Always use this constructor. Assigns the model, the object to be edited, initializes the window (components and layout), and fills the text fields with corresponding values.
     * @param phymodel the model of the simulation, having the addresses of all simulation objects
     * @indexToEdit the index of the element to edit in the model's vector
     */    
    EditRISView(PHYModel phymodel, int indexToEdit)
    {
        super(phymodel);
        this.XToEdit = phymodel.getRISAt(indexToEdit);
        initComponents();
        refreshFields();
    }

    /**
     * The initialization of the window. Specifies how does it look like. This is different for every type of element.
     */    
    protected final void initComponents()
    {
        titleLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        posxLabel = new javax.swing.JLabel();
        posxField = new javax.swing.JTextField();
        posyLabel = new javax.swing.JLabel();
        posyField = new javax.swing.JTextField();
        massLabel = new javax.swing.JLabel();
        massField = new javax.swing.JTextField();
        velxField = new javax.swing.JTextField();
        velxLabel = new javax.swing.JLabel();
        velyField = new javax.swing.JTextField();
        velyLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        nameField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        dxField = new javax.swing.JTextField();
        dxLabel = new javax.swing.JLabel();
        dyField = new javax.swing.JTextField();
        dyLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Properties");
        setAlwaysOnTop(true);
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        titleLabel.setText("Rectangular Isolator Space");

        posxLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        posxLabel.setText("Position X:");

        posxField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        posyLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        posyLabel.setText("Position Y:");

        posyField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        massLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        massLabel.setText("Mass:");

        massField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        velxField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        velxLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        velxLabel.setText("Velocity X:");

        velyField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        velyLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        velyLabel.setText("Velocity Y:");

        cancelButton.setText("Cancel");

        okButton.setText("OK");

        nameField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameLabel.setText("Name:");

        dxField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        dxLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dxLabel.setText("Dimension X:");

        dyField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        dyLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dyLabel.setText("Dimension Y:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(posxLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(posyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(massLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(velxLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(velyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameField)
                            .addComponent(posxField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(posyField)
                            .addComponent(massField)
                            .addComponent(velxField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(velyField)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(okButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dxLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(dyLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dyField, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(dxField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posxLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(massField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(massLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(velxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(velxLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(velyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(velyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dxLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }       

    /**
     * Returns DimensionX field's value.
     * @return the value specified at the text field dxField.
     */    
    public String getdxField()
    {
        try{
            return dxField.getText();
        }
        catch (NullPointerException e){
            return "0";
        }
    }  
    
    /**
     * Returns DimensionY field's value.
     * @return the value specified at the text field dyField.
     */    
    public String getdyField()
    {
        try{
            return dyField.getText();
        }
        catch (NullPointerException e){
            return "0";
        }
    }    
    
    /**
     * Fills the text fields for Dimension X, and Dimension Y with the edited objects actual attributes.
     */    
    public void refreshFields()
    {
        super.refreshFields();
        dxField.setText(strPre(((RectangularIsolatorSpace)XToEdit).getDimension().getX()));
        dyField.setText(strPre(((RectangularIsolatorSpace)XToEdit).getDimension().getY()));        
    }    
}
