/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gager
 */
public class CurrentPlaying extends javax.swing.JPanel {

    /**
     * Creates new form CurrentPlaying
     */
    public CurrentPlaying() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        
        if(RunGame.numberPlayers== 4) {
        jTable1.setBackground(new java.awt.Color(153, 51, 255));
        jTable1.setForeground(new java.awt.Color(102, 255, 102));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {RunGame.playerNames[0],RunGame.players[0].getPlayerResources()[0], RunGame.players[0].getPlayerResources()[1], RunGame.players[0].getPlayerResources()[2], RunGame.players[0].getPlayerResources()[3], RunGame.players[0].getPlayerResources()[4], RunGame.players[0].getPlanet()},
                {RunGame.playerNames[1],RunGame.players[1].getPlayerResources()[0], RunGame.players[1].getPlayerResources()[1], RunGame.players[1].getPlayerResources()[2], RunGame.players[1].getPlayerResources()[3], RunGame.players[1].getPlayerResources()[4], RunGame.players[1].getPlanet()},
                {RunGame.playerNames[2],RunGame.players[2].getPlayerResources()[0], RunGame.players[2].getPlayerResources()[1], RunGame.players[2].getPlayerResources()[2], RunGame.players[2].getPlayerResources()[3], RunGame.players[2].getPlayerResources()[4], RunGame.players[2].getPlanet()},
                {RunGame.playerNames[3],RunGame.players[3].getPlayerResources()[0], RunGame.players[3].getPlayerResources()[1], RunGame.players[3].getPlayerResources()[2], RunGame.players[3].getPlayerResources()[3], RunGame.players[3].getPlayerResources()[4], RunGame.players[3].getPlanet()}
            },
            new String [] {
                "Player", "Parts", "Fuel", "Money", "Necessities", "Titanium", "Location"
            }
        ));} else if(RunGame.numberPlayers == 3) {
        	jTable1.setBackground(new java.awt.Color(153, 51, 255));
            jTable1.setForeground(new java.awt.Color(102, 255, 102));
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {RunGame.playerNames[0],RunGame.players[0].getPlayerResources()[0], RunGame.players[0].getPlayerResources()[1], RunGame.players[0].getPlayerResources()[2], RunGame.players[0].getPlayerResources()[3], RunGame.players[0].getPlayerResources()[4], RunGame.players[0].getPlanet()},
                    {RunGame.playerNames[1],RunGame.players[1].getPlayerResources()[0], RunGame.players[1].getPlayerResources()[1], RunGame.players[1].getPlayerResources()[2], RunGame.players[1].getPlayerResources()[3], RunGame.players[1].getPlayerResources()[4], RunGame.players[1].getPlanet()},
                    {RunGame.playerNames[2],RunGame.players[2].getPlayerResources()[0], RunGame.players[2].getPlayerResources()[1], RunGame.players[2].getPlayerResources()[2], RunGame.players[2].getPlayerResources()[3], RunGame.players[2].getPlayerResources()[4], RunGame.players[2].getPlanet()}
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
                    {RunGame.playerNames[0],RunGame.players[0].getPlayerResources()[0], RunGame.players[0].getPlayerResources()[1], RunGame.players[0].getPlayerResources()[2], RunGame.players[0].getPlayerResources()[3], RunGame.players[0].getPlayerResources()[4], RunGame.players[0].getPlanet()},
                    {RunGame.playerNames[1],RunGame.players[1].getPlayerResources()[0], RunGame.players[1].getPlayerResources()[1], RunGame.players[1].getPlayerResources()[2], RunGame.players[1].getPlayerResources()[3], RunGame.players[1].getPlayerResources()[4], RunGame.players[1].getPlanet()}
                },
                new String [] {
                    "Player", "Parts", "Fuel", "Money", "Necessities", "Titanium", "Location"
                }
            ));
        }
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
