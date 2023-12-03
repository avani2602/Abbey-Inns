import java.util.LinkedList;

class Room {
  private int beds;
  private boolean hasBath;
  private boolean hasGoodView;
  private double costPerNight;

  public Room(int beds, boolean hasBath, boolean hasGoodView, double costPerNight) {
    this.beds = beds;
    this.hasBath = hasBath;
    this.hasGoodView = hasGoodView;
    this.costPerNight = costPerNight;
  }

  public int getBeds() {
    return beds;
  }

  public void setBeds(int beds) {
    this.beds = beds;
  }

  public boolean isHasBath() {
    return hasBath;
  }

  public void setHasBath(boolean hasBath) {
    this.hasBath = hasBath;
  }

  public boolean isHasGoodView() {
    return hasGoodView;
  }

  public void setHasGoodView(boolean hasGoodView) {
    this.hasGoodView = hasGoodView;
  }

  public double getCostPerNight() {
    return costPerNight;
  }

  public void setCostPerNight(double costPerNight) {
    this.costPerNight = costPerNight;
  }
}

class Hotel {
  private Room[][] rooms;
  private String name;
  private String address;
  private int stars;

  public Hotel(int floors, int roomsPerFloor, String name, String address, int stars) {
    this.rooms = new Room[floors][roomsPerFloor];
    this.name = name;
    this.address = address;
    this.stars = stars;
  }

  public void addRoom(int floor, int roomNumber, int beds, boolean hasBath, boolean hasGoodView, double costPerNight) {
    rooms[floor - 1][roomNumber - 1] = new Room(beds, hasBath, hasGoodView, costPerNight);
  }

  public void viewRooms() {
    System.out.println("Rooms for Hotel: " + name);
    for (int floor = 0; floor < rooms.length; floor++) {
      for (int roomNumber = 0; roomNumber < rooms[floor].length; roomNumber++) {
        Room room = rooms[floor][roomNumber];
        if (room != null) {
          System.out.println("Floor " + (floor + 1) + ", Room " + (roomNumber + 1) + ":");
          System.out.println("Beds: " + room.getBeds());
          System.out.println("Has Bath: " + room.isHasBath());
          System.out.println("Has Good View: " + room.isHasGoodView());
          System.out.println("Cost per Night: " + room.getCostPerNight());
          System.out.println("------------------------");
        }
      }
    }
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public int getStars() {
    return stars;
  }

  public Room[][] getRooms() {
    return rooms;
  }

  public void editRoomDetails(int floor, int roomNumber, int beds, boolean hasBath, boolean hasGoodView,
      double costPerNight) {
    if (floor >= 1 && floor <= rooms.length && roomNumber >= 1 && roomNumber <= rooms[floor - 1].length) {
      Room room = rooms[floor - 1][roomNumber - 1];
      if (room != null) {
        room.setBeds(beds);
        room.setHasBath(hasBath);
        room.setHasGoodView(hasGoodView);
        room.setCostPerNight(costPerNight);
        System.out.println("Room details updated successfully!");
      } else {
        System.out.println("Room not found!");
      }
    } else {
      System.out.println("Invalid floor or room number!");
    }
  }
}

class Company {
  private LinkedList<Hotel> hotels;
  private String CEO;
  private double profit;

  public Company() {
    this.hotels = new LinkedList<>();
  }

  public void addHotel(Hotel hotel) {
    hotels.add(hotel);
  }

  public void viewHotels() {
    for (Hotel hotel : hotels) {
      System.out.println("Hotel: " + hotel.getName());
      hotel.viewRooms();
      System.out.println("------------------------");
    }
  }

  public void editHotelDetails(String newCEO, double newProfit) {
    this.CEO = newCEO;
    this.profit = newProfit;
    System.out.println("Company details updated successfully!");
  }

  public void writeToCSV(String filename) {
    AQAWriteTextFile writer = new AQAWriteTextFile(filename);
    for (Hotel hotel : hotels) {
      writer.writeToTextFile(
          hotel.getName() + "," + hotel.getAddress() + "," + hotel.getStars());
      for (int floor = 0; floor < hotel.getRooms().length; floor++) {
        for (int roomNumber = 0; roomNumber < hotel.getRooms()[floor].length; roomNumber++) {
          Room room = hotel.getRooms()[floor][roomNumber];
          if (room != null) {
            writer.writeToTextFile(floor + 1 + "," + (roomNumber + 1) + "," + room.getBeds() + ","
                + room.isHasBath() + "," + room.isHasGoodView() + "," + room.getCostPerNight());
          }
        }
      }
    }
    writer.closeFile();
    System.out.println("Data written to CSV successfully!");
  }
}

public class Main {
  public static void main(String[] args) {
    Company company = new Company();

    Hotel hotel1 = new Hotel(3, 3, "Hotel1", "Address1", 4);
    hotel1.addRoom(1, 1, 2, true, true, 55);
    hotel1.addRoom(1, 2, 2, false, false, 65);
    // Add more rooms...

    Hotel hotel2 = new Hotel(3, 3, "Hotel2", "Address2", 3);
    hotel2.addRoom(1, 1, 2, true, true, 55);
    hotel2.addRoom(1, 2, 2, false, false, 65);
    hotel2.addRoom(3, 2, 2, true, true, 74);
    // Add more rooms...

    Hotel hotel3 = new Hotel(3, 3, "Hotel3", "Address3", 5);
    hotel3.addRoom(1, 1, 2, true, true, 55);
    hotel3.addRoom(3, 2, 1, false, true, 120);
    hotel3.addRoom(2, 3, 1, true, false, 90);

    company.addHotel(hotel1);
    company.addHotel(hotel2);
    company.addHotel(hotel3);

    // View hotels and rooms
    company.viewHotels();

    // Edit company details
    company.editHotelDetails("New CEO", 1500000.0);

    // Write data to CSV
    company.writeToCSV("AbbeyInnsUpdated.csv");
  }
}
