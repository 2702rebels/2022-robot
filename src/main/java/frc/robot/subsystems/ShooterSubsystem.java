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
    flywheel.configFactoryDefault();
    flywheel.config_kP(0, 1);
    flywheel.config_kI(0, 0);
    flywheel.config_kD(0, 0);
    flywheel.config_kF(0, 0);

    topRoller.configFactoryDefault();
    topRoller.setInverted(true);
    topRoller.config_kP(0, 0.1);
    topRoller.config_kI(0, 0);
    topRoller.config_kD(0, 0);
    topRoller.config_kF(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double flyWheelRPM = (flywheel.getSelectedSensorVelocity() * ShooterConstants.kTalonCountsToRPM);
    SmartDashboard.putNumber("Flywheel Speed", flyWheelRPM);
    double topRollerRPM = (topRoller.getSelectedSensorVelocity() * ShooterConstants.kTalonCountsToRPM);
    SmartDashboard.putNumber("TopRoller Speed", topRollerRPM);
  }

  public void shoot(double rpm) {
    System.out.println("We shooting boiz");
    flywheel.set(ControlMode.Velocity, rpm / ShooterConstants.kTalonCountsToRPM);
    topRoller.set(ControlMode.Velocity, rpm / ShooterConstants.kTalonCountsToRPM);
  }

  public void stop() {
    System.out.println("Now we ain't");
    flywheel.set(ControlMode.PercentOutput, 0);
    topRoller.set(ControlMode.PercentOutput, 0);
  }

}
