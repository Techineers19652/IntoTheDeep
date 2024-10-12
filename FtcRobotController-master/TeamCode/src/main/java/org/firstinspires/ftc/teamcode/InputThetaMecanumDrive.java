package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "InputThetaMecanumDrive")
public class InputThetaMecanumDrive extends LinearOpMode {


    @Override
    public void runOpMode() {
        //initizlaizaiton
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        double drive = gamepad1.left_stick_y; // up and down
        double strafe = gamepad1.left_stick_x; // turning left and right
        double turn = gamepad1.right_stick_x; // strafing linearly left and right

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
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD) ;
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);



        // setting to ecnoder for more accurate for later purposes
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        double frontLeftMotor;
        double frontRightMotor;
        double backLeftMotor;
        double backRightMotor;
        while (opModeIsActive()) {
            //set power levels

            double theta = Math.atan2(drive, strafe);
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
            telemetry.update();

        }

    }
}
