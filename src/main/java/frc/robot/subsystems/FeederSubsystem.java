// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.FeederConstants;

public class FeederSubsystem extends SubsystemBase {

  private VictorSPX m_rightSide = new VictorSPX(FeederConstants.kFeederStage1RightMotorCanId);
  private VictorSPX m_leftSide = new VictorSPX(FeederConstants.kFeederStage1LeftMotorCanId);
  private VictorSPX m_stage2 = new VictorSPX(FeederConstants.kFeederStage2MotorCanId);
  private double m_intakeSpeed = 0;
  private double m_feedSpeed = 0;

  /** Creates a new FeederSubsystem. */
  public FeederSubsystem() {
    m_rightSide.configFactoryDefault();

    m_leftSide.configFactoryDefault();
    m_leftSide.setInverted(InvertType.InvertMotorOutput);

    m_stage2.configFactoryDefault();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    m_intakeSpeed = SmartDashboard.getNumber("Feeder/IntakeSpeed", 0.5);
    m_feedSpeed = SmartDashboard.getNumber("Feeder/FeedSpeed", 0.3);
  }

  public void intake() {
    m_rightSide.set(ControlMode.PercentOutput, m_intakeSpeed);
    m_leftSide.set(ControlMode.PercentOutput, m_intakeSpeed);
  }

  private void feedStage1() {
    m_rightSide.set(ControlMode.PercentOutput, m_feedSpeed);
    m_leftSide.set(ControlMode.PercentOutput, m_feedSpeed);
  }

  private void feedStage2() {
    m_stage2.set(ControlMode.PercentOutput, m_feedSpeed);
  }

  public Command feedCommand() {
    return new InstantCommand(() -> feedStage1()).andThen(new WaitCommand(1)).andThen(new InstantCommand(() -> feedStage2()));
  }

  public void stop() {
    m_rightSide.set(ControlMode.PercentOutput, 0);
    m_leftSide.set(ControlMode.PercentOutput, 0);
    m_stage2.set(ControlMode.PercentOutput, 0);
  }

}
