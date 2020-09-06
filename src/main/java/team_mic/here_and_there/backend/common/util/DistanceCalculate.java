package team_mic.here_and_there.backend.common.util;

public class DistanceCalculate {

  public static Double calculateDistanceMeter(Double userLatitude, Double userLongitude,
      Double locationLatitude, Double locationLongitude) {

    double theta = userLongitude - locationLongitude;
    double distance =
        Math.sin(degreeToRadius(userLatitude)) * Math.sin(degreeToRadius(locationLatitude))
            + Math.cos(degreeToRadius(userLatitude)) * Math.cos(degreeToRadius(locationLatitude))
            * Math.cos(degreeToRadius(theta));

    distance = radiusToDegree(Math.acos(distance));
    distance *= (60 * 1.1515);
    distance *= 1609.344;

    return distance;
  }

  public static double degreeToRadius(double degree) {
    return (degree * Math.PI / 180.0);
  }

  public static double radiusToDegree(double radius) {
    return (radius * 180 / Math.PI);
  }
}
