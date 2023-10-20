// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  TalonFX flywheel = new TalonFX(ShooterConstants.kShooterStage1MotorId);
  TalonFX topRoller = new TalonFX(ShooterConstants.kShooterStage2MotorId);

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    topRoller.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double flyWheelRPM = (flywheel.getSelectedSensorVelocity() / ShooterConstants.kTalonStepsPerRotation) * 600;
    SmartDashboard.putNumber("Flywheel Speed", flyWheelRPM);
  }

  //rpm = 600
  public void shoot(double rpm) {
    double encoderPer100MS = rpm / 600 * ShooterConstants.kTalonStepsPerRotation;
    flywheel.set(ControlMode.Velocity, encoderPer100MS);
    topRoller.set(ControlMode.Velocity, encoderPer100MS);
  }

  public void stop() {
    flywheel.set(ControlMode.PercentOutput, 0);
    topRoller.set(ControlMode.PercentOutput, 0);
  }


}
