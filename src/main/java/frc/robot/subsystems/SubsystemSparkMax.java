// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

public class SubsystemSparkMax extends SubsystemBase {
  /** Creates a new SubsystemNeo. */
  private CANSparkMax sparkMax;
  private RelativeEncoder encoder;

  public SubsystemSparkMax(int ID) {
    sparkMax = new CANSparkMax(ID, MotorType.kBrushless);
    encoder = sparkMax.getEncoder();
  }

  public void setPower(double power){
    sparkMax.set(power);
  }
  public void resetEncoders(){
    encoder.setPosition(0);
  }
  public void getEncoders(){
    encoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
