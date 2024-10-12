package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "TeamMecanumTeleop")
public class TeamMecanumTeleop extends LinearOpMode {


    @Override
    public void runOpMode() {
        //initizlaizaiton
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        double drive = -gamepad1.left_stick_y; // up and down
        double strafe = gamepad1.left_stick_x; // turning left and right
        double turn = gamepad1.right_stick_x; // strafing linearly left and right

        // setting to ecnoder for later purposes
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();

        while(opModeIsActive()){
        //set power levels
        frontRight.setPower(drive-turn-strafe);
        backRight.setPower(drive-turn+strafe);
        frontLeft.setPower(drive+turn+strafe);
        backLeft.setPower(drive+turn-strafe);




        }








    }





}
