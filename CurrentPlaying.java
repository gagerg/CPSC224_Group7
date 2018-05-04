/*
 *This panel is a table of all the players in the game. Put into a frame, a button can be pressed at any time during 
 the game and will display all of the players and their current resources and location.
 */

/**
 *
 * @author Gage Gutmann, Andrew Brodhead, Alexa Andrews
 */
public class CurrentPlaying extends javax.swing.JPanel {

    /**
     * Creates new form CurrentPlaying
     */
    public CurrentPlaying() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        
        //This series of calls on the table created determines the size of the table based on the number of players and
        //passes in the current values that the players have from the game.
        if(RunGame.numberPlayers== 4) {
        jTable1.setBackground(new java.awt.Color(153, 51, 255));
        jTable1.setForeground(new java.awt.Color(102, 255, 102));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {RunGame.players[0].getName(),RunGame.players[0].getPlayerResources()[0], RunGame.players[0].getPlayerResources()[1], RunGame.players[0].getPlayerResources()[2], RunGame.players[0].getPlayerResources()[3], RunGame.players[0].getPlayerResources()[4], RunGame.players[0].getPlanet()},
                {RunGame.players[1].getName(),RunGame.players[1].getPlayerResources()[0], RunGame.players[1].getPlayerResources()[1], RunGame.players[1].getPlayerResources()[2], RunGame.players[1].getPlayerResources()[3], RunGame.players[1].getPlayerResources()[4], RunGame.players[1].getPlanet()},
                {RunGame.players[2].getName(),RunGame.players[2].getPlayerResources()[0], RunGame.players[2].getPlayerResources()[1], RunGame.players[2].getPlayerResources()[2], RunGame.players[2].getPlayerResources()[3], RunGame.players[2].getPlayerResources()[4], RunGame.players[2].getPlanet()},
                {RunGame.players[3],RunGame.players[3].getPlayerResources()[0], RunGame.players[3].getPlayerResources()[1], RunGame.players[3].getPlayerResources()[2], RunGame.players[3].getPlayerResources()[3], RunGame.players[3].getPlayerResources()[4], RunGame.players[3].getPlanet()}
            },
            new String [] {
                "Player", "Parts", "Fuel", "Money", "Necessities", "Titanium", "Location"
            }
        ));} else if(RunGame.numberPlayers == 3) {
        	jTable1.setBackground(new java.awt.Color(153, 51, 255));
            jTable1.setForeground(new java.awt.Color(102, 255, 102));
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {RunGame.players[0].getName(),RunGame.players[0].getPlayerResources()[0], RunGame.players[0].getPlayerResources()[1], RunGame.players[0].getPlayerResources()[2], RunGame.players[0].getPlayerResources()[3], RunGame.players[0].getPlayerResources()[4], RunGame.players[0].getPlanet()},
                    {RunGame.players[1].getName(),RunGame.players[1].getPlayerResources()[0], RunGame.players[1].getPlayerResources()[1], RunGame.players[1].getPlayerResources()[2], RunGame.players[1].getPlayerResources()[3], RunGame.players[1].getPlayerResources()[4], RunGame.players[1].getPlanet()},
                    {RunGame.players[2].getName(),RunGame.players[2].getPlayerResources()[0], RunGame.players[2].getPlayerResources()[1], RunGame.players[2].getPlayerResources()[2], RunGame.players[2].getPlayerResources()[3], RunGame.players[2].getPlayerResources()[4], RunGame.players[2].getPlanet()}
                },
                new String [] {
                    "Player", "Parts", "Fuel", "Money", "Necessities", "Titanium", "Location"
                }
            ));
        }else {
        	jTable1.setBackground(new java.awt.Color(153, 51, 255));
            jTable1.setForeground(new java.awt.Color(102, 255, 102));
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {RunGame.players[0].getName(),RunGame.players[0].getPlayerResources()[0], RunGame.players[0].getPlayerResources()[1], RunGame.players[0].getPlayerResources()[2], RunGame.players[0].getPlayerResources()[3], RunGame.players[0].getPlayerResources()[4], RunGame.players[0].getPlanet()},
                    {RunGame.players[1].getName(),RunGame.players[1].getPlayerResources()[0], RunGame.players[1].getPlayerResources()[1], RunGame.players[1].getPlayerResources()[2], RunGame.players[1].getPlayerResources()[3], RunGame.players[1].getPlayerResources()[4], RunGame.players[1].getPlanet()}
                },
                new String [] {
                    "Player", "Parts", "Fuel", "Money", "Necessities", "Titanium", "Location"
                }
            ));
        }
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);
        
        //Sets the layout 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );
    }


    // Variables declaration 
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}
