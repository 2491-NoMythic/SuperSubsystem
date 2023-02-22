// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Pneumatics.PneumaticForward;
import frc.robot.commands.Pneumatics.PneumaticReverse;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SubsystemLights;
import frc.robot.subsystems.SubsystemSparkMax;
import frc.robot.subsystems.SubsystemPneumatics;
import frc.robot.subsystems.SubsystemTalon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /*
   * IMPORTANT!!!
   * The IDs of any subsystem have not been defined. This means that you have to put them in yourself
   * This is done in SmartDashboard. There should exist network tables entries with changable numbers
   * and the title of what they represent. There should also be a 'Prefrences' tab within the network
   * tables with toggles for each subsystem.
  */

  private ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private SubsystemPneumatics pneumatics; 
  private SubsystemLights lights; 
  private SubsystemTalon talon; 
  private SubsystemSparkMax sparkMax;
  
  boolean TalonExists = Preferences.getBoolean("Talon", false);
  boolean PneumaticsExists = Preferences.getBoolean("Pneumatics", false);
  boolean LightsExists = Preferences.getBoolean("Lights", false);
  boolean SparkMaxExists = Preferences.getBoolean("SparkMax", false);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    if(TalonExists){talonInit();}
    if(PneumaticsExists){pneumaticsInit();}
    if(LightsExists){lightsInit();}
    if(SparkMaxExists){sparkMaxInit();}


    Preferences.initBoolean("Talon", false);
    Preferences.initBoolean("Pneumatics", false);
    Preferences.initBoolean("Lights", false);
    configureButtonBindings();
    
  }
  
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  
  /*
  * This is where the 'init' methods start. They are made separate methods because of a feature called 
  * prefences. This allows us to turn on and off subsystems per robot without changing the code at all
  * This is helpful when using the same code on multiple robots while not having to change code
  * DONT FORGET TO SET IDs ON SMARTDASHBOARD
  */


  public void lightsInit(){
    lights = new SubsystemLights((int)SmartDashboard.getNumber("LED Count", 60), (int)SmartDashboard.getNumber("Light Port Number",0));
    SmartDashboard.putData("Trigger Lights", Commands.runOnce(()-> {lights.setLights(0, 59, (int)SmartDashboard.getNumber("R", 100), (int)SmartDashboard.getNumber("G", 100), (int)SmartDashboard.getNumber("B", 100));}, lights));
    SmartDashboard.putNumber("R", 0);
    SmartDashboard.putNumber("G", 0);
    SmartDashboard.putNumber("B", 0);
    SmartDashboard.putNumber("LED Count", 0);
    SmartDashboard.putNumber("Light Port Number", 0);
  }
  
  public void pneumaticsInit(){
    pneumatics = new SubsystemPneumatics((int)SmartDashboard.getNumber("Forward Port", 0), (int)(SmartDashboard.getNumber(("Reverse Port"), 1)));
    PneumaticForward tiltForward = new PneumaticForward(pneumatics);
    PneumaticReverse tiltReverse = new PneumaticReverse(pneumatics);
    SmartDashboard.putData("Forward", tiltForward);
    SmartDashboard.putData("Reverse", tiltReverse);
    SmartDashboard.putNumber("Forward Port", 1);
    SmartDashboard.putNumber("Reverse Port", 2);
  }
  public void talonInit(){
    talon = new SubsystemTalon((int)(SmartDashboard.getNumber("Talon ID", 0)));
    SmartDashboard.putNumber("Talon DrivePower", 0);
    SmartDashboard.putNumber("Talon ID", 0);
    SmartDashboard.putData("Talon DrivePower", Commands.run(()->{talon.setPower(SmartDashboard.getNumber("Motor Power", 0));}, talon)); 
  }
  public void sparkMaxInit(){
    sparkMax = new SubsystemSparkMax((int)SmartDashboard.getNumber("SparkMax ID", 0));
    SmartDashboard.putNumber("SparkMax ID", 0);
    SmartDashboard.putNumber("SparkMax DrivePower", 0);
    SmartDashboard.putData("SparkMax DrivePower", Commands.run(()->{sparkMax.setPower(SmartDashboard.getNumber("Motor Power", 0));}, sparkMax)); 
  }
}

