/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Chassis extends SubsystemBase {
    private DutyCycleEncoder rightEncoder = new DutyCycleEncoder(0);

    private final TalonSRX talon1 = new TalonSRX(Constants.DriveBase.talon1);
    private final TalonSRX talon2 = new TalonSRX(Constants.DriveBase.talon2);
    private final TalonSRX talon3 = new TalonSRX(Constants.DriveBase.talon3);
    private final TalonSRX talon4 = new TalonSRX(Constants.DriveBase.talon4);

    private Gyro gyro = new ADXRS450_Gyro();

    private double angleTolerance = 3;
    private double speedReduction = 1 / 5;
    private double reductionIncrement = 5;
    private double reductions = 4;

    private double rightEncoderOrigin = 0;

    public void initEncoder() {
        rightEncoder.setDistancePerRotation(Constants.DISTANCE_PER_REVOLUTION);
    }

    public void zeroEncoder() {

        rightEncoderOrigin = rightEncoder.getDistance();
    }

    public void encoderDriveForward(double distance) {
        if (rightEncoder.getDistance() - rightEncoderOrigin < distance) {
            talon1.set(ControlMode.PercentOutput, -0.4);
            talon2.set(ControlMode.PercentOutput, -0.4);
            talon3.set(ControlMode.PercentOutput, 0.4);
            talon4.set(ControlMode.PercentOutput, 0.4);
        } else {
            talon1.set(ControlMode.PercentOutput, 0);
            talon2.set(ControlMode.PercentOutput, 0);
            talon3.set(ControlMode.PercentOutput, 0);
            talon4.set(ControlMode.PercentOutput, 0);
        }
    }

    public void readEncoder() {
        System.out.println(rightEncoder.getDistance());
    }

    public void drive(double leftSpeed, double rightSpeed) {
        talon1.set(ControlMode.PercentOutput, -leftSpeed);
        talon2.set(ControlMode.PercentOutput, -leftSpeed);
        talon3.set(ControlMode.PercentOutput, rightSpeed);
        talon4.set(ControlMode.PercentOutput, rightSpeed);
    }

    public void initializeGyro() {
        gyro.calibrate();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {

        }
        gyro.reset();
    }

    public void zeroGyro() {
        gyro.reset();
    }

    // function for turning
    public boolean turn(double rotationSpeed, double rotationDegrees) {
        boolean isDone = false;
        double currentDegrees = gyro.getAngle();
        System.out.println(gyro.getAngle());
        if (Math.abs(currentDegrees) < rotationDegrees * (1 - angleTolerance)) {
            talon1.set(ControlMode.PercentOutput, -rotationSpeed);
            talon2.set(ControlMode.PercentOutput, -rotationSpeed);
            talon3.set(ControlMode.PercentOutput, -rotationSpeed);
            talon4.set(ControlMode.PercentOutput, -rotationSpeed);

            for (int i = 0; i < reductions; i++) {
                if (Math.abs(currentDegrees) > speedReduction * rotationDegrees
                        + (1 - speedReduction) / reductionIncrement * rotationDegrees * i) {
                    talon1.set(ControlMode.PercentOutput,
                            -rotationSpeed * speedReduction * (reductionIncrement - i) / reductionIncrement);
                    talon2.set(ControlMode.PercentOutput,
                            -rotationSpeed * speedReduction * (reductionIncrement - i) / reductionIncrement);
                    talon3.set(ControlMode.PercentOutput,
                            -rotationSpeed * speedReduction * (reductionIncrement - i) / reductionIncrement);
                    talon4.set(ControlMode.PercentOutput,
                            -rotationSpeed * speedReduction * (reductionIncrement - i) / reductionIncrement);
                }
            }
        } else {

            talon1.set(ControlMode.PercentOutput, 0);
            talon2.set(ControlMode.PercentOutput, 0);
            talon3.set(ControlMode.PercentOutput, 0);
            talon4.set(ControlMode.PercentOutput, 0);

            isDone = true;
            return isDone;
        }
        return isDone;
    }
}
