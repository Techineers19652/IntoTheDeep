package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "ControllerTest")
public class ContollerTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");


        waitForStart();



        while(opModeIsActive()){


            boolean y = gamepad1.y;
            boolean x = gamepad1.x;
            boolean b = gamepad1.b;
            boolean a = gamepad1.a;


        if (y==true) {
            frontLeft.setPower(.1);
        } else {
            frontLeft.setPower(0);
        }
            if (x==true) {
                backLeft.setPower(.1);
            } else {
                backLeft.setPower(0);
            }
            if (b==true) {
                backRight.setPower(.1);
            } else {
               backRight.setPower(0);
            }
            if (a==true) {
                frontRight.setPower(.1);
            } else {
                frontRight.setPower(0);
            }


        telemetry.addData("y value:",y);
            telemetry.addData("x value:",x);
            telemetry.addData("a value:",a);
            telemetry.addData("b value: ",b);
            telemetry.update();


        }
    }
}
