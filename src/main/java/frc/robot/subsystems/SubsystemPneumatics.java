// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.Constants.Pneumatics;
import edu.wpi.first.wpilibj.Compressor;


public class SubsystemPneumatics extends SubsystemBase {
  /** Creates a new Pneumatics. */
  private DoubleSolenoid solenoid;
  private Compressor compressor;

  public SubsystemPneumatics(int Forward, int Reverse) {
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Forward, Reverse);
    compressor.enableDigital();
  }

  public void setForward(){
    solenoid.set(Value.kForward);
  }

  public void setReverse(){
    solenoid.set(Value.kReverse);
  }

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
