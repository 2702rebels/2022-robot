// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;



public class IntakeSubsystem extends SubsystemBase {
  // There's no Talano steps-per-rotation defined so its probably not a Talon motor
  // What is it?
  
  private final DoubleSolenoid m_piston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 
         IntakeConstants.kIntakeForwardsPcmId, IntakeConstants.kIntakeBackwardsPcmId);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void start() {
    // Fire pneumatic solenoid to drop the intake
    m_piston.set(Value.kForward);

    // Turn on the motor - do we need to be able to reverse it?
  }

  public void stop() {
    // Stop the motor
    m_piston.set(Value.kReverse);
    // Retract the intake by reversing the pneumatic
  }
}