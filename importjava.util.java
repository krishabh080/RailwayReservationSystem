import java.util.ArrayList;
import java.util.Scanner;

class Train {
    String name;
    String source;
    String destination;
    String time;
    int passengerStrength;
    int trainNumber;

    public Train(String name, String source, String destination, String time, int passengerStrength, int trainNumber) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.time = time;
        this.passengerStrength = passengerStrength;
        this.trainNumber = trainNumber;
    }
}

class Ticket {
    String passengerName;
    int seatNumber;
    int trainNumber;

    public Ticket(String passengerName, int seatNumber, int trainNumber) {
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.trainNumber = trainNumber;
    }
}

class RailwayReservationSystem {
    static ArrayList<Train> trains = new ArrayList<>();
    static ArrayList<Ticket> bookedTickets = new ArrayList<>();
    static int seatCount = 50;

    public static void main(String[] args) {
        initializeTrains();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Railway Reservation System");
            System.out.println("1. Train Information");
            System.out.println("2. Seat Availability");
            System.out.println("3. Booking");
            System.out.println("4. Canceling");
            System.out.println("5. Ticket Display");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showTrainInformation(scanner);
                    break;
                case 2:
                    checkSeatAvailability(scanner);
                    break;
                case 3:
                    bookTicket(scanner);
                    break;
                case 4:
                    cancelTicket(scanner);
                    break;
                case 5:
                    displayTicket(scanner);
                    break;
                case 6:
                    System.out.println("Thank you for using Railway Reservation System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeTrains() {
        trains.add(new Train("Mumbai Superfast", "Mumbai", "Delhi", "13:05", 50, 1010));
        trains.add(new Train("Delhi SuperFast", "Delhi", "Jaipur", "7:00", 50, 2013));
        trains.add(new Train("Prayagraj EXP", "Prayagraj", "Delhi", "10:00", 50, 3045));
    }

    private static void showTrainInformation(Scanner scanner) {
        System.out.print("Enter source station: ");
        String source = scanner.nextLine().trim();
        System.out.print("Enter destination station: ");
        String destination = scanner.nextLine().trim();

        for (Train train : trains) {
            if (train.source.equalsIgnoreCase(source) && train.destination.equalsIgnoreCase(destination)) {
                System.out.println("Train Name: " + train.name);
                System.out.println("Train Number: " + train.trainNumber);
                System.out.println("Departure Time: " + train.time);
                System.out.println("Passenger Strength: " + train.passengerStrength);
                System.out.println();
            }
        }
    }

    private static void checkSeatAvailability(Scanner scanner) {
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();

        for (Train train : trains) {
            if (train.trainNumber == trainNumber) {
                int availableSeats = seatCount - (int) bookedTickets.stream().filter(ticket -> ticket.trainNumber == trainNumber).count();

                System.out.println("Available Seats: " + availableSeats);
                return;
            }
        }

        System.out.println("Train not found.");
    }

    private static void bookTicket(Scanner scanner) {
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        for (Train train : trains) {
            if (train.trainNumber == trainNumber) {
                System.out.print("Enter passenger name: ");
                String passengerName = scanner.nextLine().trim();

                if (seatCount > 0) {
                    int seat = 50 - seatCount + 1;
                    bookedTickets.add(new Ticket(passengerName, seat, trainNumber));
                    seatCount--;
                    System.out.println("Ticket booked successfully. Your seat number is " + seat);
                } else {
                    System.out.println("No more seats available on this train.");
                }
                return;
            }
        }

        System.out.println("Train not found.");
    }

    private static void cancelTicket(Scanner scanner) {
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine().trim();

        for (Ticket ticket : bookedTickets) {
            if (ticket.trainNumber == trainNumber && ticket.passengerName.equalsIgnoreCase(passengerName)) {
                seatCount++;
                bookedTickets.remove(ticket);
                System.out.println("Ticket canceled successfully.");
                return;
            }
        }

        System.out.println("Ticket not found.");
    }

    private static void displayTicket(Scanner scanner) {
       System.out.print("Enter train number: ");
       int trainNumber = scanner.nextInt();
       scanner.nextLine();  // Consume newline character

       if (trainNumber < 1010 || trainNumber > 3045) {
        System.out.println("Invalid train number.");
        return;
     }

System.out.print("Enter passenger name: ");
String passengerName = scanner.nextLine().trim();


        for (Ticket ticket : bookedTickets) {
            if (ticket.trainNumber == trainNumber && ticket.passengerName.equalsIgnoreCase(passengerName)) {
                System.out.println("Train Name: " + trains.get(trainNumber - 1010).name);
                System.out.println("Train Number: " + trainNumber);
                System.out.println("Passenger Name: " + passengerName);
                System.out.println("Seat Number: " + ticket.seatNumber);
                return;
            }
        }

        System.out.println("Ticket not found.");
    }
}
