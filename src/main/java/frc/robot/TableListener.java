/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TableListener {
    
    public static void main(String[] args) {
        new TableListener().run();
    }
    
    public void run() {
        // get the default instance of NetworkTables
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        
        // get a reference to the subtable called "datatable"
        NetworkTable table = inst.getTable("datatable");
        
        // get a reference to key in "datatable" called "Y"
        
        NetworkTableEntry yEntry = table.getEntry("Y");
        inst.startClientTeam(190);
        
        // add an entry listener for changed values of "X", the lambda ("->" operator)
        // defines the code that should run when "X" changes
        table.addEntryListener("X", (NetworkTable, key, entry, value, flags) -> {
            System.out.println("X changed value: " + value.getValue());
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        
        // add an entry listener for changed values of "Y", the lambda ("->" operator)
        // defines the code that should run when "Y" changes
        yEntry.addListener(event -> {
            System.out.println("Y changed value: " + yEntry.getValue());
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
            Thread.currentThread().interrupt();
            return;
        }
    }
}
