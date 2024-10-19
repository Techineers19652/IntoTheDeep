package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "DriveTrainPolarityTest")
public class DriveTrainPolarityTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");


        waitForStart();

        while(opModeIsActive()){


            ///if everythign is set right it should go forward,back,left,right,

            if(gamepad1.y) {
                frontLeft.setPower(.1);
                backLeft.setPower(.1);
                frontRight.setPower(.1);
                backRight.setPower(.1);
            }

            if(gamepad1.a) {
                frontLeft.setPower(-.1);
                backLeft.setPower(-.1);
                frontRight.setPower(-.1);
                backRight.setPower(-.1);
            }
            if(gamepad1.b){
                frontLeft.setPower(.1);
                backLeft.setPower(.1);
                frontRight.setPower(-.1);
                backRight.setPower(-.1);

            }
                if(gamepad1.x){
                    frontLeft.setPower(-.1);
                    backLeft.setPower(-.1);
                    frontRight.setPower(.1);
                    backRight.setPower(.1);
                }
            telemetry.addData("FrontLeftMotor: ",frontLeft);
            telemetry.addData("BackLeftMotor: ",backLeft);
            telemetry.addData("FrontRightMotor: ",frontRight);
            telemetry.addData("BackRightMotor: ",backRight);
            telemetry.update();
        }

    }
}
