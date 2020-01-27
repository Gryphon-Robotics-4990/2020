package frc.robot.vision;

import static frc.robot.vision.Constants.*;

import edu.wpi.first.wpilibj.util.Units;


// https://www.wired.com/2012/01/projectile-motion-primer-for-first-robotics/
// http://docs.limelightvision.io/en/latest/cs_aimandrange.html

public class TargetFinder {

    public TargetFinder() {
        //
    }


    public static double findShooterVelocityToTarget() {
        double d = findDistanceToTarget(), h = TARGET_HEIGHT - LIMELIGHT_HEIGHT + d * AIR_RESISTANCE_FACTOR, a = SHOOTER_ANGLE;

    }
    
    public static double findShooterAngleToTarget(double velocity) {
        //Returns optimal firing angle given a velocity
        return 0;
    }

    //Estimates air resistance rather than finding its exact value
    public static double estimateShooterAngleToTarget() {
        double d = findDistanceToTarget();
        double h = TARGET_HEIGHT - LIMELIGHT_HEIGHT + d * AIR_RESISTANCE_FACTOR;
        double a = -1 * GRAVITY * d * d / 2 / INITIAL_VELOCITY / INITIAL_VELOCITY, b = d, c = -1 * GRAVITY * d * d / 2 / INITIAL_VELOCITY / INITIAL_VELOCITY - h;
        double p1 = Math.atan((-1 * b - Math.sqrt(b * b - 4 * a * c)) / (2 * a)), p2 = Math.atan((-1 * b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));

        //Modify to return the higher one that has a max height of less than a constant (representing the roof)
        return Math.max(p1, p2) * 180 / Math.PI;
    }

    // http://docs.limelightvision.io/en/latest/cs_estimating_distance.html
    public static double findDistanceToTarget() {
        return (TARGET_HEIGHT - LIMELIGHT_HEIGHT) / Math.tan(findAngleToTarget());
    }

    public static double findAngleToTarget() {
        return Units.degreesToRadians(LIMELIGHT_ANGLE_DEGREES) + Units.degreesToRadians(Limelight.getCrosshairVerticalOffset());
    }
}
