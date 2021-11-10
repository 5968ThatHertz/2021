package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
    Created by Westfield Robotics
    23 September, 2021

    ---------- 5968 TeleOp Program ----------
    Stick Forwards - Move Forwards
    Stick Backwards - Move Backwards
    Left Bumper - Turn left
    Right Bumper - Turn right
    Left Trigger - Arm Down
    Right Trigger - Arm Up
    B - Claw Open
    A - Claw Close
    X - Turntable Servo
    Y - Arm Reset
*/

/*
    ----- To Do ------
    Arm Reset Command (Click a button to have arm bottom out)
    Direction Blind Turning
*/

@TeleOp(name = "5968TeleOp", group = "TeleOp")
public class TeleOp5968 extends OpMode {
    
    // Motor Declarations
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backleftMotor;
    private DcMotor backRightMotor;
    private DcMotor arm;
    private CRServo claw;
    private CRServo turntable;

    // Speed Variables
    double driveSpeed;
    double armSpeed;
    double clawSpeed;
    double turntableSpeed;

    public void init() {

        // Motor Mapping
        frontLeftMotor = hardwaremap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwaremap.dcMotor.get("frontRightMotor");
        backleftMotor = hardwaremap.dcMotor.get("backLeftMotor");
        backRightMotor = hardwaremap.dcMotor.get("backRightMotor");
        arm = hardwaremap.dcMotor.get("arm");
        claw = hardwaremap.crserver.get("claw");
        turntable = hardwaremap.crservo.get("turntable");

        // Speed Values
        driveSpeed = 1;
        armSpeed = 0.25;
        clawSpeed = 0.4;
        turntableSpeed = 0.4;
    }

    public void loop() {
        // Forwards
        if (gamepad1.left_stick_y > 0.2) {
            frontLeftMotor.setPower(driveSpeed);
            frontRightMotor.setPower(driveSpeed);
            backleftMotor.setPower(driveSpeed);
            backRightMotor.setPower(driveSpeed);
        }

        // Backwards
        else if (gamepad1.left_stick_y < 0.2) {
            frontLeftMotor.setPower(-driveSpeed);
            frontRightMotor.setPower(-driveSpeed);
            backleftMotor.setPower(-driveSpeed);
            backRightMotor.setPower(-driveSpeed);
        }
        
        // Left
        else if (gamepad1.left_bumper) {
            frontLeftMotor.setPower(-driveSpeed);
            frontRightMotor.setPower(driveSpeed);
            backleftMotor.setPower(-driveSpeed);
            backRightMotor.setPower(driveSpeed);
        }
        
        // Right
        else if (gamepad1.right_bumper) {
            frontLeftMotor.setPower(driveSpeed);
            frontRightMotor.setPower(-driveSpeed);
            backleftMotor.setPower(driveSpeed);
            backRightMotor.setPower(-driveSpeed);
        }

        // Drive Reset
        else {
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backleftMotor.setPower(0);
            backRightMotor.setPower(0);
        }

        // Arm Down
        if (gamepad1.left_trigger > 0.2) {
            arm.setPower(-armSpeed);
        }

        // Arm Up
        else if (gamepad1.right_trigger < 0.2) {
            arm.setPower(armSpeed);
        }

        // Arm Reset
        else if (gamepad1.y) {
            System.out.print("In progress...");
        }

        // Arm Stop
        else {
            arm.setPower(0);
        }

        // Claw Open
        if (gamepad1.b) {
            claw.setPower(clawSpeed);
        }

        // Claw Close
        else if (gamepad1.a) {
            claw.setPower(-clawSpeed);
        }

        // Claw Stop
        else {
            claw.setPower(0);
        }

        // Turntable On
        if (gamepad1.x) {
            turntable.setPower(servoSpeed);
        }

        // Turntable Off
        else {
            turntable.setPower(0);
        }
    }
}