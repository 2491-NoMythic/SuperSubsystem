// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SubsystemTalon extends SubsystemBase {
  /** Creates a new SubsystemTalon. */
  private TalonFX motor;
  public SubsystemTalon(int motorID) {
    motor = new TalonFX(motorID);
  }

  public void setPower(double power){
    motor.set(ControlMode.PercentOutput, power);
  }

  public void resetEncoders(){
    motor.setSelectedSensorPosition(0);
  }

  public double getEncoder(){
    return motor.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
