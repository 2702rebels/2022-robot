// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.FeederSubsystem;

public class RobotContainer {

  FeederSubsystem m_feeder = new FeederSubsystem();
  private CommandXboxController m_driverController =
    new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // m_driverController.rightStick().onTrue(new InstantCommand(() -> m_drive.toggleGearbox()));
    m_driverController.a().onTrue(new InstantCommand(()-> m_feeder.intake()));
    m_driverController.b().onTrue(new InstantCommand(()-> m_feeder.stop()));
    m_driverController.x().onTrue(m_feeder.feedCommand());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}