package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "InputThetaMecanumDrive")
public class InputThetaMecanumDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //initizlaizaiton



        /*need to set right direction later on to check should look like this

         this is supposefd to look like a car the {11} are wheels the plus and minus determine direction
        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        \\\\\\+\||||||||||||||||||||||||||||+\\\\\\\\
        \\\\\{11}||                      ||{11}\\\\\\
        \\\\\\-|||                       |||\-\\\\\\\
        \\\\\\\\|||                      |||\\\\\\\\\
        \\\\\\\\|||                      |||\\\\\\\\\
        \\\\\\\\|||                      |||\\\\\\\\\
        \\\\\\\\|||                      |||\\\\\\\\\
        \\\\\\\\|||                      |||\\\\\\\\\
        \\\\\\+\|||                      |||\+\\\\\\\
        \\\\\{11}||                      ||{11}\\\\\\
        \\\\\\-||||||||||||||||||||||||||||\\-\\\\\\\
        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

         */

        //use above for diagram



        waitForStart();

        while(opModeIsActive()) {
            //set power levels
            DcMotor frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
            DcMotor backLeft = hardwareMap.get(DcMotor.class, "BackLeft");
            DcMotor frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
            DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
            double drive = -gamepad1.left_stick_y; // up and down
            double strafe = gamepad1.left_stick_x; // turning left and right
            double turn = gamepad1.right_stick_x; // strafing linearly left and right
            double frontLeftMotor;
            double frontRightMotor;
            double backLeftMotor;
            double backRightMotor;

            double theta = Math.atan2(drive,strafe);
            double power = Math.hypot(strafe, drive);
            double sin = Math.sin(theta - Math.PI / 4);
            double cos = Math.cos(theta - Math.PI / 4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));

            frontLeftMotor = power * cos / max + turn;
            frontRightMotor = power * sin / max - turn;
            backLeftMotor = power * sin / max + turn;
            backRightMotor = power * cos / max - turn;

            // Normalize motor powers if any exceeds 1.0
            double maxMotorPower = Math.max(1.0,Math.max(Math.abs(frontLeftMotor), Math.max(Math.abs(frontRightMotor),
                                    Math.max(Math.abs(backLeftMotor), Math.abs(backRightMotor)))));

            if (maxMotorPower > 1.0) {
                frontLeftMotor /= maxMotorPower;
                frontRightMotor /= maxMotorPower;
                backLeftMotor /= maxMotorPower;
                backRightMotor /= maxMotorPower;
            }
            frontLeft.setPower(frontLeftMotor);
            backLeft.setPower(backLeftMotor);
            frontRight.setPower(frontRightMotor);
            backRight.setPower(backRightMotor);
            telemetry.addData("FrontLeftMotor: ",frontLeftMotor);
            telemetry.addData("BackLeftMotor: ",backLeftMotor);
            telemetry.addData("FrontRightMotor: ",frontRightMotor);
            telemetry.addData("BackRightMotor: ",backRightMotor);

            telemetry.addData("x LJoystick values:",drive);
            telemetry.addData("y LJoystick values: ",strafe);
            telemetry.addData("x RJoystick values: ",turn);

            telemetry.update();
            telemetry.update();

        }

    }
}
