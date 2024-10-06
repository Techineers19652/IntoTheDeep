package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "AngleMecanumTeleop")
public class AngleMecanumTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //initialization of motors
        DcMotorEx leftBack;
        DcMotorEx leftFront;
        DcMotorEx rightBack;
        DcMotorEx rightFront;

        leftBack = hardwareMap.get(DcMotorEx.class, "leftMotorBack");
        leftFront = hardwareMap.get(DcMotorEx.class, "leftMotorFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightMotorBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightMotorFront");


        telemetry.addData("Initialization:", "success!");
        double x;
        double y;
        double turn;
        double theta;
        double power;

waitForStart();
        while (opModeIsActive()) {
            x = gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;

            theta = Math.atan2(y,x);
            power = Math.hypot(x,y);
            double sin = Math.sin(theta - Math.PI/4);
            double cos = Math.cos(theta - Math.PI/4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));

            double leftFrontPower = Range.clip(power * cos/max + turn,-1,1);
            double rightFrontPower =  Range.clip(power * sin/max - turn,-1,1);
            double leftBackPower = Range.clip(power * sin/max + turn,-1,1);
            double rightBackPower = Range.clip(power * cos/max - turn,-1,1);

            leftBack.setPower(leftBackPower);
            leftFront.setPower(rightFrontPower);
            rightBack.setPower(rightBackPower);
            rightFront.setPower(leftFrontPower);



        }


    }
}